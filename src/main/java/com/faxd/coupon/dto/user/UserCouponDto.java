package com.faxd.coupon.dto.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class UserCouponDto {
    private Set<Long> users;
    private String platform;
    private String couponCode;

    public static List<String> getNotNullAttrList(){
        return new ArrayList<String>(
                Arrays.asList("users", "platform","couponCode")
        );
    }

    public UserCouponDto(Set<Long> users, String platform, String couponCode) {
        this.users = users;
        this.platform = platform;
        this.couponCode = couponCode;
    }

    public UserCouponDto() {
    }

    public Set<Long> getUsers() {
        return users;
    }

    public void setUsers(Set<Long> users) {
        this.users = users;
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

    @Override
    public String toString() {
        return "UserCouponDto{" +
                "users=" + users +
                ", platform='" + platform + '\'' +
                ", couponCode='" + couponCode + '\'' +
                '}';
    }
}
