package com.example.grocery.demo.controller;

import com.example.grocery.demo.model.Cart;
import com.example.grocery.demo.model.Item;
import com.example.grocery.demo.model.Receipt;
import com.example.grocery.demo.service.CartService;
import com.example.grocery.demo.service.CouponService;
import com.example.grocery.demo.service.ReceiptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class ReceiptControllerTest {

    @InjectMocks
    private ReceiptController receiptController;

    @Mock
    private ReceiptService receiptService;

    @Mock
    private CouponService couponService;

    @Mock
    private CartService cartService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(receiptController).build();
    }

    @Test
    public void testCalculateReceipt() throws Exception {
        Cart cart = createCart(); // Create a cart instance here
        String cartJson = convertCartToJson(cart);

        Receipt receipt = new Receipt(); // Create a receipt instance here

        // Set the properties of the receipt instance using variables
        double expectedSubtotal = 30;
        double expectedTaxTotal = 3;
        double expectedGrandTotal = 33;

        receipt.setSubtotal(expectedSubtotal);
        receipt.setTaxTotal(expectedTaxTotal);
        receipt.setGrandTotal(expectedGrandTotal);

        when(receiptService.calculateSubtotalAndTax(cart)).thenReturn(receipt);

        // The JSON representation of the cart object should be passed as the content
        mockMvc.perform(post("/api/receipts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartJson))
                .andExpect(status().isOk());
/*                .andExpect(jsonPath("$.subtotal").value(expectedSubtotal))
                .andExpect(jsonPath("$.taxTotal").value(expectedTaxTotal))
                .andExpect(jsonPath("$.grandTotal").value(expectedGrandTotal));*/
    }


    @Test
    public void testGenerateReceiptFromResources() throws Exception {
        Cart cart = createCart(); // Create a cart instance here
        String cartJson = convertCartToJson(cart);

        Receipt receipt = new Receipt(); // Create a receipt instance here

        // Set the properties of the receipt instance using variables
        double expectedSubtotal = 30;
        double expectedTaxTotal = 3;
        double expectedGrandTotal = 33;

        receipt.setSubtotal(expectedSubtotal);
        receipt.setTaxTotal(expectedTaxTotal);
        receipt.setGrandTotal(expectedGrandTotal);

        when(cartService.loadCartFromFile()).thenReturn(cart);
        when(receiptService.calculateSubtotalAndTax(cart)).thenReturn(receipt);

        mockMvc.perform(get("/api/receipts/generate-receipt"))
                .andExpect(status().isOk());
    }


    private Cart createCart() {
        Item item1 = new Item("Item1", 123,true,true,3);
        Item item2 = new Item("Item2", 876,false,true,4);
        return new Cart(Arrays.asList(item1, item2));
    }

    private String convertCartToJson(Cart cart) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(cart);
    }
}
