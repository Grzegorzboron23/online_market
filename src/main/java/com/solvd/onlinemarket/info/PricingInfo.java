package com.solvd.onlinemarket.info;

import com.solvd.onlinemarket.exception.InvalidValueException;

import java.math.BigDecimal;

public class PricingInfo {

    private BigDecimal price;
    private Integer quantity;

    public PricingInfo(BigDecimal price, Integer quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidValueException("Price cannot be negative.");
        }
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new InvalidValueException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }
}
