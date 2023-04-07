package com.example.grocery.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coupon {

    @JsonProperty("couponName")
    private String couponName;

    @JsonProperty("appliedSku")
    private long appliedSku;

    @JsonProperty("discountPrice")
    private double discountPrice;

    public Coupon() {
    }

    public Coupon(String couponName, long appliedSku, double discountPrice) {
        this.couponName = couponName;
        this.appliedSku = appliedSku;
        this.discountPrice = discountPrice;
    }

    // Getters and setters
    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public long getAppliedSku() {
        return appliedSku;
    }

    public void setAppliedSku(long appliedSku) {
        this.appliedSku = appliedSku;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
