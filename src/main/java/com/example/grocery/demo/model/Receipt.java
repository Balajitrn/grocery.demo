package com.example.grocery.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Receipt {

    private double subtotal;
    private double taxTotal;
    private double grandTotal;

    public Receipt() {
    }

    public Receipt(double subtotal, double taxTotal, double grandTotal) {
        this.subtotal = subtotal;
        this.taxTotal = taxTotal;
        this.grandTotal = grandTotal;
    }

    @JsonIgnore
    public double getSubtotal() {
        return round(subtotal, 2);
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    @JsonIgnore
    public double getTaxTotal() {
        return round(taxTotal, 2);
    }

    public void setTaxTotal(double taxTotal) {
        this.taxTotal = taxTotal;
    }

    @JsonIgnore
    public double getGrandTotal() {
        return round(grandTotal, 2);
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @JsonProperty("Subtotal")
    public String getFormattedSubtotal() {
        return formatCurrency(getSubtotal());
    }

    @JsonProperty("TaxTotal")
    public String getFormattedTaxTotal() {
        return formatCurrency(getTaxTotal());
    }

    @JsonProperty("GrandTotal")
    public String getFormattedGrandTotal() {
        return formatCurrency(getGrandTotal());
    }

    private String formatCurrency(double value) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(value);
    }
}
