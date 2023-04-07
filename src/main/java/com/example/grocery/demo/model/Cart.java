package com.example.grocery.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Cart {

    @JsonProperty("items")
    private List<Item> items;

    public Cart() {
    }

    public Cart(List<Item> items) {
        this.items = items;
    }
    // Getters and setters
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(items, cart.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }


}
