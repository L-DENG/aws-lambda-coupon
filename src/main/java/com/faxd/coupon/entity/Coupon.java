package com.faxd.coupon.entity;

import com.faxd.coupon.constant.CouponCodeType;
import java.time.OffsetDateTime;
import java.util.Set;

public class Coupon {

    private String platform;
    private String couponCode;
    private Float discount;
    private CouponCodeType codeType;
    private Set<Long> users;
    private Boolean isUserRestricted;
    private Set<String> products;
    private Boolean isProductRestricted;
    private Integer amountUsed;
    private Integer amountTotal;
    private Float minimumPurchase;
    private OffsetDateTime commenceDate;
    private OffsetDateTime expireDate;
    private Boolean isValid;
    private OffsetDateTime  createdTime;
    private OffsetDateTime  updatedTime;

    public Coupon(String platform, String couponCode,
                  Float discount, CouponCodeType codeType,
                  Set<Long> users, Boolean isUserRestricted,
                  Set<String> products, Boolean isProductRestricted,
                  Integer amountUsed, Integer amountTotal, Float minimumPurchase,
                  OffsetDateTime commenceDate, OffsetDateTime expireDate,
                  Boolean isValid, OffsetDateTime createdTime, OffsetDateTime updatedTime) {
        this.platform = platform;
        this.couponCode = couponCode;
        this.discount = discount;
        this.codeType = codeType;
        this.users = users;
        this.isUserRestricted = isUserRestricted;
        this.products = products;
        this.isProductRestricted = isProductRestricted;
        this.amountUsed = amountUsed;
        this.amountTotal = amountTotal;
        this.minimumPurchase = minimumPurchase;
        this.commenceDate = commenceDate;
        this.expireDate = expireDate;
        this.isValid = isValid;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Coupon(){

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


    public Set<Long> getUsers() {
        return users;
    }

    public void setUsers(Set<Long> users) {
        this.users = users;
    }

    public Integer getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Integer amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Integer getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Integer amountUsed) {
        this.amountUsed = amountUsed;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Boolean getUserRestricted() {
        return isUserRestricted;
    }

    public void setUserRestricted(Boolean userRestricted) {
        isUserRestricted = userRestricted;
    }

    public Set<String> getProducts() {
        return products;
    }

    public void setProducts(Set<String> products) {
        this.products = products;
    }

    public Boolean getProductRestricted() {
        return isProductRestricted;
    }

    public void setProductRestricted(Boolean productRestricted) {
        isProductRestricted = productRestricted;
    }
}
