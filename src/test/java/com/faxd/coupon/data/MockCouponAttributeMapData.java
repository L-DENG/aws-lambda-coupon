package com.faxd.coupon.data;


import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.HashMap;
import java.util.Map;

public class MockCouponAttributeMapData {

    public static final String PLATFORM_FIELD = "platform";
    public static final String COUPON_CODE_FIELD = "couponCode";
    public static final String DISCOUNT_FIELD = "discount";
    public static final String CODE_TYPE_FIELD = "codeType";
    public static final String AMOUNT_FIELD = "amountTotal";
    public static final String MINIMUM_PURCHASE_FIELD = "minimumPurchase";
    public static final String COMMERCE_DATE_FIELD = "commenceDate";
    public static final String EXPIRE_DATE_FIELD = "expireDate";
    public static final String CREATE_TIME_FIELD = "createdTime";
    public static final String UPDATED_TIME_FIELD = "updatedTime";

    public static Map<String, AttributeValue> getCouponAttributeMap(){
        Map<String, AttributeValue> map = new HashMap<>();
        map.put(PLATFORM_FIELD, AttributeValue.builder().s(MockCouponData.PLATFORM).build());
        map.put(COUPON_CODE_FIELD, AttributeValue.builder().s(MockCouponData.COUPON_CODE).build());
        map.put(DISCOUNT_FIELD, AttributeValue.builder().n(MockCouponData.DISCOUNT.toString()).build());
        map.put(CODE_TYPE_FIELD, AttributeValue.builder().s(String.valueOf(MockCouponData.CODE_TYPE)).build());
        map.put(AMOUNT_FIELD, AttributeValue.builder().n(MockCouponData.AMOUNT_TOTAL.toString()).build());
        map.put(MINIMUM_PURCHASE_FIELD, AttributeValue.builder().n(MockCouponData.MINIMUM_PURCHASE.toString()).build());
        map.put(COMMERCE_DATE_FIELD, AttributeValue.builder().s(MockCouponData.COMMERCE_DATE.toString()).build());
        map.put(EXPIRE_DATE_FIELD, AttributeValue.builder().s(MockCouponData.EXPIRE_DATE.toString()).build());
//        map.put(CREATE_TIME_FIELD, AttributeValue.builder().s(MockCouponData.MINIMUM_PURCHASE.toString()).build());
//        map.put(UPDATED_TIME_FIELD, AttributeValue.builder().s(MockCouponData.MINIMUM_PURCHASE.toString()).build());

        return map;
    }
}
