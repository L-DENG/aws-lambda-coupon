package com.faxd.coupon.data;

import com.faxd.coupon.constant.CouponCodeType;
import com.faxd.coupon.dto.coupon.CouponGetDto;
import com.faxd.coupon.dto.coupon.CouponPostDto;

import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;

public class MockCouponData {

    public static final String PLATFORM = "platform-test01";
    public static final String COUPON_CODE = "save20";
    public static final Float DISCOUNT = 20F;
    public static final CouponCodeType CODE_TYPE = CouponCodeType.MONEY_OFF;
    public static final Long AMOUNT_TOTAL = 1000L;
    public static final Float MINIMUM_PURCHASE = 150F;
    public static final OffsetDateTime COMMERCE_DATE = OffsetDateTime.now(ZoneId.systemDefault());
    public static final Integer PERIOD_EXPIRY_DAY = 10;
    public static final OffsetDateTime EXPIRE_DATE = OffsetDateTime.now(ZoneId.systemDefault()).plus(Period.ofDays(PERIOD_EXPIRY_DAY));

    public static CouponPostDto getMockCouponPostDtoFull() {
        CouponPostDto couponPostDto = new CouponPostDto();
        couponPostDto.setPlatform(PLATFORM);
        couponPostDto.setCouponCode(COUPON_CODE);
        couponPostDto.setDiscount(DISCOUNT);
        couponPostDto.setCodeType(CODE_TYPE);
        couponPostDto.setAmountTotal(AMOUNT_TOTAL);
        couponPostDto.setMinimumPurchase(MINIMUM_PURCHASE);
        couponPostDto.setCommenceDate(COMMERCE_DATE);
        couponPostDto.setExpireDate(EXPIRE_DATE);

        return couponPostDto;
    }

    public static CouponPostDto getMockCouponPostDtoMissingPlatform() {
        CouponPostDto couponPostDto = new CouponPostDto();
        couponPostDto.setCouponCode(COUPON_CODE);
        couponPostDto.setDiscount(DISCOUNT);
        couponPostDto.setCodeType(CODE_TYPE);
        couponPostDto.setAmountTotal(AMOUNT_TOTAL);
        couponPostDto.setMinimumPurchase(MINIMUM_PURCHASE);

        return couponPostDto;
    }


    public static CouponGetDto getMockCouponGetDto() {
        CouponGetDto couponGetDto = new CouponGetDto();
        couponGetDto.setPlatform(PLATFORM);
        couponGetDto.setCouponCode(COUPON_CODE);
        couponGetDto.setDiscount(DISCOUNT);
        couponGetDto.setCodeType(CODE_TYPE);
        couponGetDto.setAmountTotal(AMOUNT_TOTAL);
        couponGetDto.setMinimumPurchase(MINIMUM_PURCHASE);
        return couponGetDto;
    }





}
