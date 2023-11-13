package com.faxd.coupon.constant;

public enum CouponCodeType {
    MONEY_OFF("money_off"),
    PERCENT_OFF("percent_off");

    private final String name;

    CouponCodeType(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static CouponCodeType getEnumByString(String type) {
        for (CouponCodeType e : CouponCodeType.values()) {
            if (e.getName().equals(type)) return e;
        }
        return null;
    }
}
