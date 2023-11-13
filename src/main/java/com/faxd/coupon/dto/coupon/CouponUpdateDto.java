package com.faxd.coupon.dto.coupon;

import com.faxd.coupon.constant.CouponCodeType;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class CouponUpdateDto {

    private String platform;
    private String couponCode;
    private Float discount;
    private CouponCodeType codeType;
    private Boolean isUserRestricted;
    private Boolean isProductRestricted;
    private long amountLeft;
    private long amountTotal;
    private Float minimumPurchase;
    private OffsetDateTime commenceDate;
    private OffsetDateTime expireDate;

    public static List<String> getNotNullAttrList(){
        return new ArrayList<String>(
//                Arrays.asList("couponCode", "CouponId")
        );
    }

}
