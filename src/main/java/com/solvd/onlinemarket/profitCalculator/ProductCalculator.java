package com.solvd.onlinemarket.profitCalculator;

import com.solvd.onlinemarket.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Final class: This class is marked as final to prevent inheritance.
public final class ProductCalculator {

    //    is declared static because it is a constant value
//    common to all instances. Represents a general discount
//    final because it's rule that is the same for all products.
    private static final BigDecimal AVAILABLE_DISCOUNT = BigDecimal.valueOf(5);

    static {
        System.out.println("ProductCalculator class loaded. AVAILABLE_DISCOUNT set to 5%");
    }

    public static BigDecimal calculateProfit(BigDecimal price, BigDecimal productionCost) {
        return price.subtract(productionCost);
    }

    //     used because they are utility methods. Their operation is independent of the object
    public static BigDecimal applyDiscount(Product product) {
        BigDecimal discountFactor = BigDecimal.ONE.subtract(AVAILABLE_DISCOUNT.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));

        BigDecimal currentPrice = product.getPricingInfo().getPrice();
        BigDecimal discountedPrice = currentPrice.multiply(discountFactor);

        product.getPricingInfo().setPrice(discountedPrice);

        return discountedPrice;
    }

    public static BigDecimal getAvailableDiscount() {
        return AVAILABLE_DISCOUNT;
    }
}

