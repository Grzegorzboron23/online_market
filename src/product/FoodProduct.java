package product;

import attribute.Size;
import enumeration.Category;
import info.PricingInfo;
import info.ProductBasicInfo;

public class FoodProduct extends Product {

    private static final Category category = Category.FOOD;
    private final String brand;
    private Boolean isOrganic;

    public FoodProduct(ProductBasicInfo basicInfo, PricingInfo pricingInfo,
                       Size size, String brand) {
        super(basicInfo, pricingInfo, size);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public Boolean getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(Boolean isOrganic) {
        this.isOrganic = isOrganic;
    }

}
