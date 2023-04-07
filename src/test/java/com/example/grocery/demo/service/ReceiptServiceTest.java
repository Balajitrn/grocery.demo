package com.example.grocery.demo.service;

import com.example.grocery.demo.model.Cart;
import com.example.grocery.demo.model.Coupon;
import com.example.grocery.demo.model.Item;
import com.example.grocery.demo.model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {

    @InjectMocks
    private ReceiptService receiptService;

    @Mock
    private CouponService couponService;

    private Cart cart;
    private Map<Long, Coupon> coupons;

    @BeforeEach
    public void setUp() {
        // Create cart and coupons objects for testing
        Item item1 = new Item("Item1", 1L, true, false, 10);
        Item item2 = new Item("Item2", 2L, false, false, 20);
        cart = new Cart(Arrays.asList(item1, item2));

        Coupon coupon1 = new Coupon("COUPON1", 10, 0.79);
        Coupon coupon2 = new Coupon("COUPON2", 5, 0.50);
        coupons = new HashMap<>();
        coupons.put(coupon1.getAppliedSku(), coupon1);
        coupons.put(coupon2.getAppliedSku(), coupon2);

    }

    @Test
    public void testCalculateReceipt() {
        // When
        Receipt receipt = receiptService.calculateSubtotalAndTax(cart);

        // Then
        assertEquals(30, receipt.getSubtotal());
        assertEquals(30.83, receipt.getGrandTotal());
    }
}
