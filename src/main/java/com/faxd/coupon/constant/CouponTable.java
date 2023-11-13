package com.faxd.coupon.constant;

public enum CouponTable {
    COUPON_TABLE("coupon_table"),
    PARTITION_KEY("platform"),
    SORT_KEY("couponCode");

    private final String name;

    CouponTable(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


}
