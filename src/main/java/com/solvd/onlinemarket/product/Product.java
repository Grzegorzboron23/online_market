package com.solvd.onlinemarket.product;


import com.solvd.onlinemarket.attribute.Size;
import com.solvd.onlinemarket.info.PricingInfo;
import com.solvd.onlinemarket.info.ProductBasicInfo;
import com.solvd.onlinemarket.info.ProductDetailsInfo;

public abstract class Product {

    //    In this program com.solvd.onlinemarket.product cannot change basicInfo
    private final ProductBasicInfo basicInfo;
    private PricingInfo pricingInfo;
    private Size size;
    private ProductDetailsInfo productDetails;

    public Product(ProductBasicInfo basicInfo, PricingInfo pricingInfo, Size size) {
        this.basicInfo = basicInfo;
        this.pricingInfo = pricingInfo;
        this.size = size;
    }

    public ProductBasicInfo getBasicInfo() {
        return basicInfo;
    }

    public PricingInfo getPricingInfo() {
        return pricingInfo;
    }

    public void setPricingInfo(PricingInfo pricingInfo) {
        this.pricingInfo = pricingInfo;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ProductDetailsInfo getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetailsInfo productDetails) {
        this.productDetails = productDetails;
    }

    // Final method to calculate the surface area of the rectangular prism represented by this Size.
    //final to cannot override method in subclasses
    public final Float calculateSurfaceArea() {
        return 2 * (
                size.getWidth() * size.getHeight() +
                        size.getHeight() * size.getDepth() +
                        size.getWidth() * size.getDepth()
        );
    }

}
