package com.solvd.product;

import com.solvd.attribute.Size;
import com.solvd.enumeration.Category;
import com.solvd.info.PricingInfo;
import com.solvd.info.ProductBasicInfo;
import com.solvd.productInterface.Deliverable;
import com.solvd.productInterface.IdentifiableByISBN;

import java.math.BigDecimal;

public class Book extends Product implements IdentifiableByISBN, Deliverable {

    private static final int DELIVERY_TIME = 5;
    private static final BigDecimal PRODUCTION_COST = BigDecimal.valueOf(2.06);
    private static final Category category = Category.BOOKS;
    private final String author;
    private final Integer pageCount;
    private final String genre;
    private final String isbn;
    private String publisher;

    public Book(ProductBasicInfo basicInfo, PricingInfo pricingInfo,
                Size size, String author, Integer pageCount, String genre, String isbn) {
        super(basicInfo, pricingInfo, size);
        if (pageCount <= 0) {
            throw new IllegalArgumentException("Page count must be greater than zero.");
        }

        this.author = author;
        this.pageCount = pageCount;
        this.genre = genre;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String getISBN() {
        return isbn;
    }

    @Override
    public boolean isValidISBN() {
        return isbn != null && (isbn.length() == 10 || isbn.length() == 13);
    }

    @Override
    public int getDeliveryTime() {
        return DELIVERY_TIME;
    }
}
