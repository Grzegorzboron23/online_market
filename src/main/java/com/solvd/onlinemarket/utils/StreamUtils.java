package com.solvd.onlinemarket.utils;

import com.solvd.onlinemarket.enumeration.Category;
import com.solvd.onlinemarket.product.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {

    private static final Logger logger = LogManager.getLogger(StreamUtils.class);


    private static boolean isValidProduct(Product product) {
        return product != null && product.getPricingInfo() != null && product.getPricingInfo().getPrice() != null;
    }

    public static Product getBiggestProductValue(List<Product> products) {
        return products.stream()
                .filter(StreamUtils::isValidProduct)
                .max(Comparator.comparing(product -> product.getPricingInfo().getPrice()))
                .orElse(null);
    }

    public static Product findFirstProductWithPriceBiggerThan(List<Product> products, BigDecimal price) {
        return products.stream()
                .filter(StreamUtils::isValidProduct)
                .filter(product -> product.getPricingInfo().getPrice().compareTo(price) > 0)
                .findFirst()
                .orElse(null);
    }

    public static List<Product> findAllProductsWithPriceBiggerThan(List<Product> products, BigDecimal price) {
        return products.stream()
                .filter(StreamUtils::isValidProduct)
                .filter(product -> product.getPricingInfo().getPrice().compareTo(price) > 0)
                .collect(Collectors.toList());
    }

    public static Integer countProductsWithPriceBiggerThan(List<Product> products, BigDecimal price) {
        return Math.toIntExact(products
                .stream()
                .filter(StreamUtils::isValidProduct)
                .filter(product -> product.getPricingInfo().getPrice().compareTo(price) > 0)
                .count());
    }

    public static List<Product> sortProductsByPrice(List<Product> products) {
        return products
                .stream()
                .filter(StreamUtils::isValidProduct)
                .sorted((product1, product2) -> product1.getPricingInfo().getPrice().compareTo(product2.getPricingInfo().getPrice()))
                .toList();
    }

    public static List<BigDecimal> getProductsPriceWithVatTax(List<Product> products, BigDecimal vat) {
        return products
                .stream()
                .filter(StreamUtils::isValidProduct)
                .map(product -> product.getPricingInfo().getPrice().multiply(vat))
                .toList();
    }

    public static void showAllPrices(List<Product> products) {
        products.stream()
                .filter(StreamUtils::isValidProduct)
                .forEach(product -> System.out.println(product.getPricingInfo().getPrice()));
    }

    public static boolean anyExpensive(List<Product> products, BigDecimal price) {
        return products.stream()
                .filter(StreamUtils::isValidProduct)
                .anyMatch(product -> product.getPricingInfo().getPrice().compareTo(price) > 0);
    }

    public static List<String> priceWithVatDetails(List<Product> products, BigDecimal vat) {
        return products.stream()
                .filter(StreamUtils::isValidProduct)
                .flatMap(product -> Stream.of(
                        "Name: " + product.getBasicInfo().getName(),
                        "Price: " + product.getPricingInfo().getPrice(),
                        "Price with VAT: " + product.getPricingInfo().getPrice().multiply(vat)
                ))
                .collect(Collectors.toList());
    }

    public static List<Product> processedProducts(List<Product> products) {
        return products.stream()
                .filter(StreamUtils::isValidProduct)
                .peek(product -> logger.info("Filtered product: {}", product.getBasicInfo().getName()))
                .sorted(Comparator.comparing(product -> product.getPricingInfo().getPrice()))
                .peek(product -> logger.info("Sorted product: {}", product.getBasicInfo().getName()))
                .collect(Collectors.toList());
    }

    public static Optional<String> findProductNameByCategory(List<Product> products, Category category) {
        if (products == null || category == null) {
            return Optional.empty();
        }

        return products.stream()
                .filter(product -> product.getBasicInfo() != null && category.equals(product.getBasicInfo().getCategory()))
                .map(product -> product.getBasicInfo().getName())
                .findFirst();
    }
}
