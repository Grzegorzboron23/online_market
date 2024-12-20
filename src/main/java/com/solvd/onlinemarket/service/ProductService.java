package com.solvd.onlinemarket.service;


import com.solvd.onlinemarket.product.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    public static <T extends Product> BigDecimal countTotalValue(List<T> products) {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (T product : products) {
            totalValue = totalValue.add(product.getPricingInfo().getPrice().multiply(
                    BigDecimal.valueOf(product.getPricingInfo().getQuantity()))
            );
        }
        return totalValue;
    }

    public static Float countTotalSpaceForProductsInWareHouse(List<Product> products) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Provide some products");
        }

        float result = 0;
        float MARGIN = 0.5f;

        for (Product product : products) {
            if (product == null) {
                LOGGER.warn("Skip null product");
                continue;
            }

            result += product.calculateSurfaceArea() + MARGIN;
        }
        return result;
    }

    public static Map<String, List<Product>> categorizeProductsBySize(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Provide some products");
        }

        Map<String, List<Product>> categorizedProducts = new HashMap<>();

        for (Product product : products) {
            if (product == null) {
                LOGGER.warn("Skipping invalid product");
                continue;
            }

            float surfaceArea = product.calculateSurfaceArea();
            String category;

            if (surfaceArea < 10) {
                category = "Small";
            } else if (surfaceArea <= 50) {
                category = "Medium";
            } else {
                category = "Large";
            }

            categorizedProducts.computeIfAbsent(category, key -> new ArrayList<>()).add(product);
        }

        return categorizedProducts;
    }
}
