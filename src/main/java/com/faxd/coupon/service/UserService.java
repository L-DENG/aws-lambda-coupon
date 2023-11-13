package com.faxd.coupon.service;

import com.faxd.coupon.dto.user.UserCouponDto;
import com.faxd.coupon.entity.Coupon;
import com.faxd.coupon.utils.DynamoFactory;
import com.faxd.coupon.utils.ObjectMapMapper;
import com.faxd.coupon.constant.CouponTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class UserService {
    private final Logger logger = LoggerFactory.getLogger(Coupon.class);
    private final DynamoDbAsyncClient dynamoDbAsyncClient;

    public UserService(){
        this.dynamoDbAsyncClient = DynamoFactory.getDynamoInstance();
    }


    public Boolean addUserToCoupon(UserCouponDto userCouponDto){
        Optional<Coupon> coupon = new CouponService().getCouponByPlatformAndCouponCode(userCouponDto.getPlatform(),
                userCouponDto.getCouponCode(),
                new Coupon());

        if (coupon.isEmpty()){
            logger.info(userCouponDto.getCouponCode() + " has not existed on the platform: " + userCouponDto.getPlatform());
            return false;
        }
        Set<Long> existUser = coupon.get().getUsers();

        logger.info("user set before: " + userCouponDto.getUsers());
        if (existUser != null){
            Set<Long> users = new HashSet<>(userCouponDto.getUsers());
            logger.info("user set from database: " + existUser);
            users.addAll(existUser);
            userCouponDto.setUsers(users);
        }
        logger.info("user set after: " + userCouponDto.getUsers());

        Map<String, AttributeValueUpdate> updateMap = ObjectMapMapper.GetUpdateMapFromObj(userCouponDto);

        AttributeValue isUserRestricted = AttributeValue.builder().bool(true).build();
        updateMap.put("isUserRestricted",AttributeValueUpdate.builder()
                .value(isUserRestricted).action(AttributeAction.PUT).build());

        logger.info("GetUpdateMapFromObj updateMap: " + updateMap);
        UpdateItemRequest updateRequest = UpdateItemRequest.builder()
                .tableName(CouponTable.COUPON_TABLE.getName())
                .key(ObjectMapMapper.getKeyMapFromObject(userCouponDto))
                .attributeUpdates(updateMap)
                .build();

        try {
            logger.info("try to update the user item: {} into table: coupon_table",updateRequest);
            UpdateItemResponse updateItemResponse = dynamoDbAsyncClient.updateItem(updateRequest).get();
            logger.info("updateItemResponse......>>>{}",updateItemResponse);
            return true;
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error processing the update request in addUserToCoupon method:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
