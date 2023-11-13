package com.faxd.coupon.dto.coupon;

import com.faxd.coupon.constant.CouponCodeType;

import java.time.OffsetDateTime;
import java.util.Set;

public class CouponGetDto {
    private String platform;
    private String couponCode;
    private Float discount;
    private CouponCodeType codeType;
    private Set<Long> users;
    private Boolean isUserRestricted;
    private Set<String> products;
    private Boolean isProductRestricted;
    private Long amountUsed;
    private Long amountTotal;
    private Float minimumPurchase;
    private OffsetDateTime commenceDate;
    private OffsetDateTime expireDate;
    private Boolean isValid;


    public CouponGetDto(String platform, String couponCode, Float discount,
                        CouponCodeType codeType, Set<Long> users, Boolean isUserRestricted,
                        Set<String> products, Boolean isProductRestricted,
                        Long amountUsed, Long amountTotal, Float minimumPurchase,
                        OffsetDateTime commenceDate, OffsetDateTime expireDate,
                        Boolean isValid) {
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
    }

    public CouponGetDto() {
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

    public Set<Long> getUsers() {
        return users;
    }

    public void setUsers(Set<Long> users) {
        this.users = users;
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

    public Long getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Long amountUsed) {
        this.amountUsed = amountUsed;
    }

    public Float getMinimumPurchase() {
        return minimumPurchase;
    }

    public void setMinimumPurchase(Float minimumPurchase) {
        this.minimumPurchase = minimumPurchase;
    }

    public Long getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Long amountTotal) {
        this.amountTotal = amountTotal;
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


    @Override
    public String toString() {
        return "CouponGetDto{" +
                "platform='" + platform + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", discount=" + discount +
                ", codeType=" + codeType +
                ", users=" + users +
                ", isUserRestricted=" + isUserRestricted +
                ", products=" + products +
                ", isProductRestricted=" + isProductRestricted +
                ", amountUsed=" + amountUsed +
                ", amountTotal=" + amountTotal +
                ", minimumPurchase=" + minimumPurchase +
                ", commenceDate=" + commenceDate +
                ", expireDate=" + expireDate +
                ", isValid=" + isValid +
                '}';
    }
}
