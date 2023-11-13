package com.faxd.coupon.data;

import java.util.HashMap;
import java.util.Map;

public class MockDataCouponQueryMap {

    public static Map<String, String> getMockKeyMap(){
        Map<String, String> mockKeyMap = new HashMap<>();
        mockKeyMap.put("platform", MockCouponData.PLATFORM);
        mockKeyMap.put("couponCode",MockCouponData.COUPON_CODE);
        return mockKeyMap;
    }

}
