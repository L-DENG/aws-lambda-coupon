package com.faxd.coupon.utils;

import com.faxd.coupon.constant.CouponCodeType;
import com.faxd.coupon.constant.CouponTable;
import com.faxd.coupon.dto.user.UserCouponDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class ObjectMapMapper {
    private static final Logger logger = LoggerFactory.getLogger(ObjectMapMapper.class);
    private static final String BOOLEAN_VALUE = "Boolean";
    private static final String STRING_VALUE = "String";
    private static final String LONG_VALUE = "Long";
    private static final String FLOAT_VALUE = "Float";
    private static final String INTEGER_VALUE = "Integer";
    private static final String SET_VALUE = "Set";
    private static final String COUPON_CODE_TYPE = "CouponCodeType";
    private static final String LOCAL_DATA_TIME_VALUE = "LocalDateTime";
    private static final String OFFSET_DATA_TIME_VALUE = "OffsetDateTime";

    public static Map<String, AttributeValue> getObjectToMap(Object obj, List<String> notNullAttributes) {
        Map<String, AttributeValue> map = new HashMap<>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value != null) {
                switch (field.getType().getSimpleName()) {
                    case BOOLEAN_VALUE ->
                            map.put(keyName, AttributeValue.builder()
                            .bool(Boolean.valueOf(value.toString())).build());
                    case SET_VALUE->{
                        String[] strings = ((Set<?>) value).stream().map(Object::toString).toArray(String[]::new);
                        map.put(keyName, AttributeValue.builder().ns(strings).build());
                    }
                    case LONG_VALUE,FLOAT_VALUE,INTEGER_VALUE ->
                            map.put(keyName, AttributeValue.builder().n(value.toString()).build());
                    default -> map.put(keyName, AttributeValue.builder().s(value.toString()).build());
                }
            }
            else if (notNullAttributes.contains(keyName)) {
                logger.error(keyName + " cannot be null");
                throw new RuntimeException(keyName + " cannot be null");
            }
        }
        return map;
    }

    public static Map<String, AttributeValueUpdate> GetUpdateMapFromObj(Object obj) {
        Map<String, AttributeValueUpdate> updateMap = new HashMap<>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            if (CouponTable.PARTITION_KEY.getName().equals(keyName) || CouponTable.SORT_KEY.getName().equals(keyName)) {
                continue;
            }
            Object value;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                logger.error("error in method GetUpdateMapFromObj: " + e.getMessage());
                throw new RuntimeException(e);
            }
            if (value != null) {
                AttributeValue updateValue;
                switch (field.getType().getSimpleName()) {
                    case BOOLEAN_VALUE ->
                        updateValue = AttributeValue.builder().bool(Boolean.valueOf(value.toString())).build();
                    case SET_VALUE -> {
                        String[] strings = ((Set<?>) value).stream()
                                .map(Object::toString)
                                .toArray(String[]::new);
                        updateValue = AttributeValue.builder().ns(strings).build();
                    }

                    case LONG_VALUE, FLOAT_VALUE, INTEGER_VALUE ->
                            updateValue = AttributeValue.builder().n(value.toString()).build();
                    default -> updateValue = AttributeValue.builder().s(value.toString()).build();
                }
                AttributeValueUpdate build = AttributeValueUpdate.builder()
                        .value(updateValue).action(AttributeAction.PUT).build();
                updateMap.put(keyName, build);
            }
        }
        AttributeValue updatedTime = AttributeValue.builder()
                .s(OffsetDateTime.now(ZoneId.systemDefault()).toString()).build();
        updateMap.put("updatedTime", AttributeValueUpdate.builder()
                .value(updatedTime).action(AttributeAction.PUT).build());

        logger.error("get map from GetUpdateMapFromObj: " + updateMap);
        return updateMap;

    }


    public static <T> T getMapToObject(Map<String, AttributeValue> map, T obj) {
        if (map == null)
            return obj;
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            if (map.containsKey(field.getName())) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                AttributeValue attrValue = map.get(field.getName());
                try {
                    switch (field.getType().getSimpleName()) {
                        case LOCAL_DATA_TIME_VALUE -> field.set(obj, Long.valueOf(attrValue.s()));
                        case STRING_VALUE -> field.set(obj, attrValue.s());
                        case FLOAT_VALUE -> field.set(obj, Float.valueOf(attrValue.n()));
                        case INTEGER_VALUE -> field.set(obj, Integer.parseInt(attrValue.n()));
                        case LONG_VALUE -> field.set(obj, Long.valueOf(attrValue.n()));
                        case COUPON_CODE_TYPE -> field.set(obj, CouponCodeType.valueOf(attrValue.s()));
                        case BOOLEAN_VALUE -> field.set(obj, attrValue.bool());
                        case SET_VALUE ->
                                field.set(obj, new HashSet<>(attrValue.ns().stream()
                                        .map(Long::valueOf)
                                        .collect(Collectors.toSet())));
                        case OFFSET_DATA_TIME_VALUE -> {
                            Instant parse = Instant.parse(attrValue.s());
                            OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(parse, ZoneId.systemDefault());
                            field.set(obj, offsetDateTime);
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    logger.error("convert {} to {} error: {}",
                            map.getClass().getName(),
                            obj.getClass().getName(),
                            e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
        return obj;
    }

    public static Map<String, AttributeValue> getKeyMapFromObject(UserCouponDto obj) {
        Class<?> cla = obj.getClass();
        Map<String, AttributeValue> primaryKeyMap = new HashMap<>();
        try {
            Field partitionKey = cla.getDeclaredField(CouponTable.PARTITION_KEY.getName());
            partitionKey.setAccessible(true);
            Object platformObj = partitionKey.get(obj);

            Field sortKey = cla.getDeclaredField(CouponTable.SORT_KEY.getName());
            sortKey.setAccessible(true);
            Object couponCodeObj = sortKey.get(obj);

            primaryKeyMap.put(CouponTable.PARTITION_KEY.getName(), AttributeValue.builder().s(platformObj.toString()).build());
            primaryKeyMap.put(CouponTable.SORT_KEY.getName(), AttributeValue.builder().s(couponCodeObj.toString()).build());
            return primaryKeyMap;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            logger.error("error processing method getKeyMapFromObject: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}