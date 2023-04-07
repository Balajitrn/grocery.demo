package com.example.grocery.demo.service;

import com.example.grocery.demo.model.Cart;
import com.example.grocery.demo.model.Item;
import com.example.grocery.demo.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * This service is responsible for handling receipt-related operations, such as
 * calculating subtotals, tax totals, and grand totals for a cart.
 */
@Service
public class ReceiptService {

    private CouponService couponService;

    /**
     * Default constructor that initializes the CouponService.
     */
    public ReceiptService() {
        couponService = new CouponService();
    }

    /**
     * Calculates the grand total for the given cart.
     *
     * @param cart the cart for which to calculate the grand total.
     * @return the grand total.
     */
    public double calculateGrandTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

    /**
     * Calculates the subtotal and tax total for the given cart and creates a Receipt object.
     *
     * @param cart the cart for which to calculate the subtotal and tax total.
     * @return a Receipt object containing the subtotal, tax total, and grand total.
     */
    public Receipt calculateSubtotalAndTax(Cart cart) {
        double subtotal = calculateGrandTotal(cart);
        double taxTotal = cart.getItems().stream()
                .filter(Item::isTaxable)
                .mapToDouble(item -> item.getPrice() * 0.0825)
                .sum();
        double grandTotal = subtotal + taxTotal;

        return new Receipt(subtotal, taxTotal, grandTotal);
    }

    /**
     * Applies tax to the items in the cart.
     *
     * @param cart the cart containing the items to which tax should be applied.
     */
    public void applyTaxToItems(Cart cart) {
        for (Item item : cart.getItems()) {
            if (item.isTaxable()) {
                double tax = item.getPrice() * 0.0825;
                item.setPrice(item.getPrice() + tax);
            }
        }
    }

    /**
     * Applies coupons to the items in the cart using the CouponService.
     *
     * @param cart the cart containing the items to which coupons should be applied.
     */
    public void applyCoupons(Cart cart) {
        couponService.applyCoupons(cart);
    }
}
