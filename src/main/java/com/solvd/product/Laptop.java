package com.solvd.product;


import com.solvd.attribute.Size;
import com.solvd.enumeration.Category;
import com.solvd.info.PricingInfo;
import com.solvd.info.ProductBasicInfo;
import com.solvd.productInterface.Configurable;
import com.solvd.productInterface.Deliverable;
import com.solvd.productInterface.Portable;

import java.math.BigDecimal;

public class Laptop extends Product implements Deliverable, Portable, Configurable {

    private static final int DELIVERY_TIME = 4;
    private static final BigDecimal PRODUCTION_COST = BigDecimal.valueOf(2.16);
    private static final Category category = Category.ELECTRONICS;
    //    Cannot change brand no setter final
    private final String brand;
    private final Double weight;
    private String processor;
    private String operatingSystem;
    private boolean isInsured;
    private int ram;

    public Laptop(ProductBasicInfo basicInfo, PricingInfo pricingInfo,
                  Size size, String brand, String processor, String operatingSystem,
                  Double weight, int ram) {
        super(basicInfo, pricingInfo, size);
        this.brand = brand;
        this.processor = processor;
        this.operatingSystem = operatingSystem;
        this.isInsured = false;
        this.ram = ram;
        this.weight = weight;
    }

    public static BigDecimal getProductionCost() {
        return PRODUCTION_COST;
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

    @Override
    public int getDeliveryTime() {
        return DELIVERY_TIME;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isLightweight() {
        return weight < 2.0;
    }

    @Override
    public void upgradeRam(int additionalGB) {
        this.ram += additionalGB;
        System.out.println("Upgraded RAM to: " + this.ram + "GB");
    }
}



