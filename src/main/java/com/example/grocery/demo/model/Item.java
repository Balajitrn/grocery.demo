package com.example.grocery.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Item {

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("sku")
    private long sku;

    @JsonProperty("isTaxable")
    private boolean isTaxable;

    @JsonProperty("ownBrand")
    private boolean ownBrand;

    @JsonProperty("price")
    private double price;

    public Item() {
    }

    public Item(String itemName, long sku, boolean isTaxable, boolean ownBrand, double price) {
        this.itemName = itemName;
        this.sku = sku;
        this.isTaxable = isTaxable;
        this.ownBrand = ownBrand;
        this.price = price;
    }

    // Getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public boolean isTaxable() {
        return isTaxable;
    }

    public void setIsTaxable(boolean isTaxable) {
        this.isTaxable = isTaxable;
    }

    public boolean getOwnBrand() {
        return ownBrand;
    }

    public void setOwnBrand(boolean ownBrand) {
        this.ownBrand = ownBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return sku == item.sku &&
                isTaxable == item.isTaxable &&
                ownBrand == item.ownBrand &&
                Double.compare(item.price, price) == 0 &&
                Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, sku, isTaxable, ownBrand, price);
    }

}
