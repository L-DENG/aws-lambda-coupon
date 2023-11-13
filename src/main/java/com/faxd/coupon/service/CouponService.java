package com.faxd.coupon.service;

import com.faxd.coupon.dto.coupon.CouponPostDto;
import com.faxd.coupon.constant.CouponTable;
import com.faxd.coupon.dto.coupon.CouponGetDto;
import com.faxd.coupon.dto.coupon.CouponSlimDto;
import com.faxd.coupon.dto.coupon.CouponUpdateDto;
import com.faxd.coupon.entity.Coupon;
import com.faxd.coupon.utils.DynamoFactory;
import com.faxd.coupon.utils.ObjectMapMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.utils.ImmutableMap;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CouponService {
    private final Logger logger = LoggerFactory.getLogger(CouponService.class);
    private final DynamoDbAsyncClient dynamoDbAsyncClient;

    public CouponService(){
        this.dynamoDbAsyncClient = DynamoFactory.getDynamoInstance();
    }

    public Boolean createCoupon(CouponPostDto couponPostDto) {
        if (getCouponByPlatformAndCouponCode(couponPostDto.getPlatform(), couponPostDto.getCouponCode(), new CouponSlimDto()).isPresent()){
            logger.warn(couponPostDto.getCouponCode() + " has existed on the platform: " + couponPostDto.getPlatform());
            return false;
        }

        couponPostDto.setCreatedTime(OffsetDateTime.now(ZoneId.systemDefault()));
        couponPostDto.setUpdatedTime(OffsetDateTime.now(ZoneId.systemDefault()));

        //default invalid
        if (couponPostDto.getValid() == null){
            couponPostDto.setValid(false);
        }

        List<String> notNullAttrList = CouponPostDto.getNotNullAttrList();
        Map<String, AttributeValue> couponMap  = ObjectMapMapper.getObjectToMap(couponPostDto,notNullAttrList);
        logger.info("converted  the couponPostDto: {} to couponMap:{}",couponPostDto,couponMap);

        PutItemRequest putItemRequest = PutItemRequest.builder().item(couponMap)
            .tableName(CouponTable.COUPON_TABLE.getName())
            .build();
        try {
            dynamoDbAsyncClient.putItem(putItemRequest).get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            logger.error("putItem failed with message: {}", e.getMessage());
            throw new RuntimeException("Error creating Put Item request");
        }
    }

    public <T> Optional<T> getCouponByPlatformAndCouponCode(String platform,String couponCode,T t) {
        Map<String, AttributeValue> primaryKeyMap = new HashMap<>();
        primaryKeyMap.put(CouponTable.PARTITION_KEY.getName(), AttributeValue.builder().s(platform).build());
        primaryKeyMap.put(CouponTable.SORT_KEY.getName(), AttributeValue.builder().s(couponCode).build());

        GetItemResponse getItemResponse;
        try {
            logger.info("try to get item from database.....");
            getItemResponse = dynamoDbAsyncClient.getItem(GetItemRequest.builder()
                            .key(primaryKeyMap)
                            .tableName(CouponTable.COUPON_TABLE.getName())
                            .build())
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("get item failed with message: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        if (getItemResponse.hasItem() && getItemResponse.item().get("isValid") != null) {
            logger.info("getItemResponse:{}", getItemResponse.item());
            return Optional.of(ObjectMapMapper.getMapToObject(getItemResponse.item(), t));
        } else {
            logger.info(" Coupon Code: {} does not exist on {}", couponCode, platform);
            return Optional.empty();
        }
    }

    public Optional<List<CouponGetDto>> getAllCouponByPlatform(String platform){
        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(CouponTable.COUPON_TABLE.getName())
                .keyConditionExpression(CouponTable.PARTITION_KEY.getName() + " = :partitionValue")
                .expressionAttributeValues(
                        ImmutableMap.of(":partitionValue",
                                AttributeValue.builder().s(platform).build())
                )
                .build();

        logger.info("try to get a batch of coupon...");
        CompletableFuture<QueryResponse> queryFuture = dynamoDbAsyncClient.query(queryRequest);
        
        logger.info("ResponseCompletableFuture: {}",queryFuture);

        List<CouponGetDto> couponList = new ArrayList<>();

        queryFuture.whenComplete((batchGetItemResponse, throwable) -> {
            if (throwable != null) {
                logger.error("Error occurred: " + throwable.getMessage());
                throw new RuntimeException("Error occurred: " + throwable.getMessage());
            }

            List<Map<String, AttributeValue>> items = batchGetItemResponse.items();
            List<CouponGetDto> coupons = items.stream()
                    .map(s -> ObjectMapMapper.getMapToObject(s, new CouponGetDto()))
                    .toList();
            couponList.addAll(coupons);
        });

        queryFuture.join();
        return Optional.of(couponList);
    }

    public Coupon updateCoupon(CouponUpdateDto couponUpdateDto){
        return null;
    }

    public Coupon deleteCouponByPlatformAndCouponCode(String platform,String couponCode){
        return null;
    }
}
