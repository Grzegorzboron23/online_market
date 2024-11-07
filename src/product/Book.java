package product;

import attribute.Size;
import enumeration.Category;
import info.PricingInfo;
import info.ProductBasicInfo;

public class Book extends Product {

    private static final Category category = Category.BOOKS;
    private final String author;
    private final Integer pageCount;
    private final String genre;
    private String publisher;

    public Book(ProductBasicInfo basicInfo, PricingInfo pricingInfo,
                Size size, String author, Integer pageCount, String genre) {
        super(basicInfo, pricingInfo, size);
        if (pageCount <= 0) {
            throw new IllegalArgumentException("Page count must be greater than zero.");
        }

        this.author = author;
        this.pageCount = pageCount;
        this.genre = genre;
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
}
