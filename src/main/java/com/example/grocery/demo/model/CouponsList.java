package com.example.grocery.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CouponsList {

    @JsonProperty("coupons")
    private List<Coupon> coupons;

    // Getters and setters
    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
