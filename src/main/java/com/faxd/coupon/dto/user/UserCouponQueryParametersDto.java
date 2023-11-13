package com.faxd.coupon.dto.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserCouponQueryParametersDto {
    private Long users;
    private String couponCode;
    private String platform;
    private List<String> products;
    private Float purchase;


    public static List<String> getNotNullAttrList(){
        return new ArrayList<String>(
                Arrays.asList("userId", "couponCode","platform","products","purchase")
        );
    }

    public UserCouponQueryParametersDto(Long users, String couponCode, String platform,
                                        List<String> products, Float purchase) {
        this.users = users;
        this.couponCode = couponCode;
        this.platform = platform;
        this.products = products;
        this.purchase = purchase;
    }

    public UserCouponQueryParametersDto() {
    }

    public Long getUsers() {
        return users;
    }

    public void setUsers(Long users) {
        this.users = users;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public Float getPurchase() {
        return purchase;
    }

    public void setPurchase(Float purchase) {
        this.purchase = purchase;
    }
}
