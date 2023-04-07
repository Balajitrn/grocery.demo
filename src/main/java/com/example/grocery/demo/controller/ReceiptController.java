package com.example.grocery.demo.controller;

import com.example.grocery.demo.model.Cart;
import com.example.grocery.demo.model.Receipt;
import com.example.grocery.demo.service.CartService;
import com.example.grocery.demo.service.CouponService;
import com.example.grocery.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private CartService cartService;


    @PostMapping
    public Receipt calculateReceipt(@RequestBody Cart cart) {
        // Apply the features' logic and return the calculated receipt
        couponService.applyCoupons(cart);
        receiptService.applyTaxToItems(cart);
        return receiptService.calculateSubtotalAndTax(cart);
    }

    @GetMapping("/load-cart")
    public Cart loadCartFromFile() throws IOException {
        return cartService.loadCartFromFile();
    }

    @GetMapping("/generate-receipt")
    public Receipt generateReceiptFromResources() throws IOException {
        Cart cart = cartService.loadCartFromFile();
        couponService.applyCoupons(cart);
        receiptService.applyTaxToItems(cart);
        return receiptService.calculateSubtotalAndTax(cart);
    }

}
