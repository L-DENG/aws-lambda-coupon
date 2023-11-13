package com.faxd.coupon.dto.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductCouponPostDto {
    private List<String> products;
    private String platform;
    private Long couponCode;

    public static List<String> getNotNullAttrList(){
        return new ArrayList<String>(
                Arrays.asList("couponCode", "platform","products")
        );
    }

    public ProductCouponPostDto(List<String> products, String platform, Long couponCode) {
        this.products = products;
        this.platform = platform;
        this.couponCode = couponCode;
    }

    public ProductCouponPostDto() {
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Long couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public String toString() {
        return "ProductCouponPostDto{" +
                "products=" + products +
                ", platform='" + platform + '\'' +
                ", couponCode=" + couponCode +
                '}';
    }
}
