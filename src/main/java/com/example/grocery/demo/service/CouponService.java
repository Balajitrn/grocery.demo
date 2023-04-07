package com.example.grocery.demo.service;

import com.example.grocery.demo.model.Cart;
import com.example.grocery.demo.model.Coupon;
import com.example.grocery.demo.model.CouponsList;
import com.example.grocery.demo.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This service is responsible for handling coupon-related operations, such as
 * loading coupons from JSON and applying coupons to a cart.
 */
@Service
public class CouponService {

    private Map<Long, Coupon> coupons;

    /**
     * Default constructor that loads the coupons from JSON file.
     */
    public CouponService() {
        loadCoupons();
    }

    /**
     * Returns the map of coupons.
     *
     * @return the map of coupons.
     */
    public Map<Long, Coupon> getCoupons() {
        return coupons;
    }

    /**
     * Loads the coupons from the JSON file and stores them in a map.
     */
    private void loadCoupons() {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/coupons.json");

        try {
            CouponsList couponsList = objectMapper.readValue(inputStream, CouponsList.class);
            List<Coupon> couponList = couponsList.getCoupons();
            coupons = new HashMap<>();

            for (Coupon coupon : couponList) {
                coupons.put(coupon.getAppliedSku(), coupon);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load coupons from coupons.json", e);
        }
    }

    /**
     * Retrieves a coupon by its SKU.
     *
     * @param sku the SKU of the coupon to retrieve.
     * @return an Optional containing the coupon if it exists, or an empty Optional otherwise.
     */
    public Optional<Coupon> getCouponBySku(long sku) {
        return Optional.ofNullable(coupons.get(sku));
    }

    /**
     * Applies coupons to the cart, updating the prices of the items in the cart accordingly.
     *
     * @param cart the cart to apply the coupons to.
     */
    public void applyCoupons(Cart cart) {
        for (Item item : cart.getItems()) {
            Optional<Coupon> optionalCoupon = getCouponBySku(item.getSku());
            optionalCoupon.ifPresent(coupon -> {
                double newPrice = Math.max(0, item.getPrice() - coupon.getDiscountPrice());
                item.setPrice(newPrice);
            });
        }
    }
}
