package com.faxd.coupon.util;

import com.faxd.coupon.data.MockCouponAttributeMapData;
import com.faxd.coupon.data.MockCouponData;
import com.faxd.coupon.dto.coupon.CouponPostDto;
import com.faxd.coupon.utils.ObjectMapMapper;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CouponMapperTest {

    private static final String MISSING_VALUE = "platform";

    @Test
    void shouldReturnMapWhenGetObjectToMapGivenCouponPostDto(){
        Map<String, AttributeValue> map = ObjectMapMapper.getObjectToMap(MockCouponData.getMockCouponPostDtoFull(), new ArrayList<>());
        assertEquals(map, MockCouponAttributeMapData.getCouponAttributeMap());
    }

    @Test
    void shouldThrowMissMissingPlatformErrorWhenGetObjectToMapGivenCouponPostDtoMissingPlatform(){
        RuntimeException platformError = assertThrows(RuntimeException.class, () -> {
            ObjectMapMapper.getObjectToMap(MockCouponData.getMockCouponPostDtoMissingPlatform(), new ArrayList<>(Arrays.asList(MISSING_VALUE)));
        });
        assertEquals( MISSING_VALUE + " cannot be null",platformError.getMessage());
    }

    @Test
    void shouldReturnCouponPostDtoWhenGetMapToObjectGivenMap(){
        CouponPostDto mapToObject = ObjectMapMapper.getMapToObject(MockCouponAttributeMapData.getCouponAttributeMap(), new CouponPostDto());
        assertEquals(MockCouponData.getMockCouponPostDtoFull().toString(),mapToObject.toString());
    }

}
