package com.example.grocery.demo.service;

import com.example.grocery.demo.model.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * This service is responsible for handling cart-related operations,
 * such as loading a cart from a JSON file.
 */
@Service
public class CartService {
    private static final String CART_JSON_FILE = "cart.json";

    /**
     * Loads a Cart object from a JSON file located in the classpath.
     *
     * @return a Cart object containing the items loaded from the JSON file.
     * @throws IOException if an error occurs while reading the JSON file.
     */
    public Cart loadCartFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource(CART_JSON_FILE).getInputStream();
        return objectMapper.readValue(inputStream, Cart.class);
    }
}
