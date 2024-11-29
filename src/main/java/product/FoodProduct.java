package product;

import attribute.Size;
import enumeration.Category;
import info.PricingInfo;
import info.ProductBasicInfo;
import productInterface.Deliverable;
import productInterface.Spoiled;

import java.math.BigDecimal;

public class FoodProduct extends Product implements Deliverable, Spoiled {

    private static final int DELIVERY_TIME = 3;
    private static final BigDecimal PRODUCTION_COST = BigDecimal.valueOf(2.96);
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

    @Override
    public int getDeliveryTime() {
        return DELIVERY_TIME;
    }

    //    our products never get spoiled :)
    @Override
    public boolean isSpoiled() {
        return false;
    }
}
