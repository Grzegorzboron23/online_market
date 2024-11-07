package service;


import product.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    public static <T extends Product> BigDecimal countTotalValue(List<T> products) {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (T product : products) {
            totalValue = totalValue.add(product.getPricingInfo().getPrice().multiply(
                    BigDecimal.valueOf(product.getPricingInfo().getQuantity()))
            );
        }
        return totalValue;
    }
}
