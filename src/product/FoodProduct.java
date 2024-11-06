package product;

import attribute.Size;
import enumeration.Category;
import info.PricingInfo;
import info.ProductBasicInfo;
import info.ProductDetailsInfo;

import java.time.LocalDateTime;

public class FoodProduct extends Product {

    private final String brand;
    private final LocalDateTime expirationDate;
    private final Boolean isOrganic;
    private static final Category category = Category.FOOD;

    private FoodProduct(Builder builder) {
        super(builder.basicInfo, builder.pricingInfo, builder.size, builder.productDetails);
        this.brand = builder.brand;
        this.expirationDate = builder.expirationDate;
        this.isOrganic = builder.isOrganic;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public Boolean getIsOrganic() {
        return isOrganic;
    }

    public static class Builder {
        private final ProductBasicInfo basicInfo;
        private final PricingInfo pricingInfo;
        private final Size size;
        private final ProductDetailsInfo productDetails;
        private String brand;
        private LocalDateTime expirationDate;
        private Boolean isOrganic;

        public Builder(ProductBasicInfo basicInfo, PricingInfo pricingInfo, Size size, ProductDetailsInfo productDetails) {
            this.basicInfo = basicInfo;
            this.pricingInfo = pricingInfo;
            this.size = size;
            this.productDetails = productDetails;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder expirationDate(LocalDateTime expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder isOrganic(Boolean isOrganic) {
            this.isOrganic = isOrganic;
            return this;
        }

        public FoodProduct build() {
            return new FoodProduct(this);
        }
    }
}

