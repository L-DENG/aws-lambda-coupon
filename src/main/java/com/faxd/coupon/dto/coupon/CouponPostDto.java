package com.faxd.coupon.dto.coupon;

import com.faxd.coupon.constant.CouponCodeType;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CouponPostDto {
    private String platform;
    private String couponCode;
    private Float discount;
    private CouponCodeType codeType;
    private Long amountTotal;
    private Float minimumPurchase;
    private OffsetDateTime commenceDate;
    private OffsetDateTime expireDate;
    private OffsetDateTime  createdTime;
    private OffsetDateTime  updatedTime;
    private Boolean isValid;

    public static List<String> getNotNullAttrList(){
        return new ArrayList<String>(
                Arrays.asList("platform","couponCode", "discount",
                        "codeType","amountTotal","minimumPurchase",
                        "commenceDate","expireDate"
                        )
        );
    }

    public CouponPostDto(String platform, String couponCode,
                         Float discount, CouponCodeType codeType,
                         long amountTotal, Float minimumPurchase,
                         OffsetDateTime commenceDate, OffsetDateTime expireDate,
                         OffsetDateTime createdTime, OffsetDateTime updatedTime,Boolean isValid) {
        this.platform = platform;
        this.couponCode = couponCode;
        this.discount = discount;
        this.codeType = codeType;
        this.amountTotal = amountTotal;
        this.minimumPurchase = minimumPurchase;
        this.commenceDate = commenceDate;
        this.expireDate = expireDate;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.isValid = isValid;
    }

    public CouponPostDto() {
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

    public long getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(long amountTotal) {
        this.amountTotal = amountTotal;
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

    public OffsetDateTime getExpire_date() {
        return expireDate;
    }

    public void setExpireDate(OffsetDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public void setAmountTotal(Long amountTotal) {
        this.amountTotal = amountTotal;
    }

    public OffsetDateTime getExpireDate() {
        return expireDate;
    }

    public OffsetDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(OffsetDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public OffsetDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(OffsetDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "CouponPostDto{" +
                "platform='" + platform + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", discount=" + discount +
                ", codeType=" + codeType +
                ", amountTotal=" + amountTotal +
                ", minimumPurchase=" + minimumPurchase +
                ", commenceDate=" + commenceDate +
                ", expireDate=" + expireDate +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", isValid=" + isValid +
                '}';
    }
}
