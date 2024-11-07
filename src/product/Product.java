package product;


import attribute.Size;
import info.PricingInfo;
import info.ProductBasicInfo;
import info.ProductDetailsInfo;

public abstract class Product {

    //    In this program product cannot change basicInfo
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
}
