package product;


import attribute.Size;
import enumeration.Category;
import info.PricingInfo;
import info.ProductBasicInfo;
import info.ProductDetailsInfo;

public class Book extends Product {

    private final String author;
    private final String publisher;
    private final Integer pageCount;
    private final String genre;
    private static final Category category = Category.BOOKS;

    public Book(Builder builder) {
        super(builder.basicInfo, builder.pricingInfo, builder.size, builder.productDetails);
        this.author = builder.author;
        this.publisher = builder.publisher;
        this.pageCount = builder.pageCount;
        this.genre = builder.genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getGenre() {
        return genre;
    }

    public static class Builder {
        private final ProductBasicInfo basicInfo;
        private final PricingInfo pricingInfo;
        private final Size size;
        private final ProductDetailsInfo productDetails;
        private String author;
        private String publisher;
        private Integer pageCount;
        private String genre;

        public Builder(ProductBasicInfo basicInfo, PricingInfo pricingInfo, Size size, ProductDetailsInfo productDetails) {
            this.basicInfo = basicInfo;
            this.pricingInfo = pricingInfo;
            this.size = size;
            this.productDetails = productDetails;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder pageCount(Integer pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
