package product;


import attribute.Size;
import enumeration.Category;
import info.PricingInfo;
import info.ProductBasicInfo;
import info.ProductDetailsInfo;

public class Laptop extends Product {

    private final String brand;
    private final String processor;
    private final String operatingSystem;
    private static final Category category = Category.ELECTRONICS;

    private Laptop(Builder builder) {
        super(builder.basicInfo, builder.pricingInfo, builder.size, builder.productDetailsInfo);
        this.brand = builder.brand;
        this.processor = builder.processor;
        this.operatingSystem = builder.operatingSystem;
    }

    public String getBrand() {
        return brand;
    }

    public String getProcessor() {
        return processor;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public static class Builder {
        private final ProductBasicInfo basicInfo;
        private final PricingInfo pricingInfo;
        private final Size size;
        private final ProductDetailsInfo productDetailsInfo;
        private String brand;
        private String processor;
        private String operatingSystem;

        public Builder(ProductBasicInfo basicInfo, PricingInfo pricingInfo, Size size, ProductDetailsInfo productDetails) {
            this.basicInfo = basicInfo;
            this.pricingInfo = pricingInfo;
            this.size = size;
            this.productDetailsInfo = productDetails;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder processor(String processor) {
            this.processor = processor;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Laptop build() {
            return new Laptop(this);
        }
    }
}


