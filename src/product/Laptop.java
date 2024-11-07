package product;


import attribute.Size;
import enumeration.Category;
import info.PricingInfo;
import info.ProductBasicInfo;

public class Laptop extends Product {

    private static final Category category = Category.ELECTRONICS;
    //    Cannot change brand no setter final
    private final String brand;
    private String processor;
    private String operatingSystem;
    private boolean isInsured;

    public Laptop(ProductBasicInfo basicInfo, PricingInfo pricingInfo,
                  Size size, String brand, String processor, String operatingSystem) {
        super(basicInfo, pricingInfo, size);
        this.brand = brand;
        this.processor = processor;
        this.operatingSystem = operatingSystem;
        this.isInsured = false;
    }

    public String getBrand() {
        return brand;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean isInsured) {
        this.isInsured = isInsured;
    }
}



