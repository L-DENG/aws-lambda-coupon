package com.faxd.coupon.dto.coupon;

import com.faxd.coupon.constant.CouponCodeType;

import java.time.OffsetDateTime;

public class CouponSlimDto {
    private String platform;
    private String couponCode;
    private Float discount;
    private CouponCodeType codeType;
    private Integer amountLeft;
    private Float minimumPurchase;
    private OffsetDateTime commenceDate;
    private OffsetDateTime expireDate;
    private Boolean isValid;

    public CouponSlimDto(String platform, String couponCode, Float discount, CouponCodeType codeType,
                         Integer amountLeft, Float minimumPurchase,
                         OffsetDateTime commenceDate, OffsetDateTime expireDate,
                         Boolean isValid) {

        this.platform = platform;
        this.couponCode = couponCode;
        this.discount = discount;
        this.codeType = codeType;
        this.amountLeft = amountLeft;
        this.minimumPurchase = minimumPurchase;
        this.commenceDate = commenceDate;
        this.expireDate = expireDate;
        this.isValid = isValid;
    }

    public CouponSlimDto() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public CouponCodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CouponCodeType codeType) {
        this.codeType = codeType;
    }

    public Integer getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(Integer amountLeft) {
        this.amountLeft = amountLeft;
    }

    public Float getMinimumPurchase() {
        return minimumPurchase;
    }

    public void setMinimumPurchase(Float minimumPurchase) {
        this.minimumPurchase = minimumPurchase;
    }

    public OffsetDateTime getCommenceDate() {
        return commenceDate;
    }

    public void setCommenceDate(OffsetDateTime commenceDate) {
        this.commenceDate = commenceDate;
    }

    public OffsetDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(OffsetDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
