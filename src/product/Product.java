package product;


import attribute.Size;
import info.PricingInfo;
import info.ProductBasicInfo;
import info.ProductDetailsInfo;

public abstract class Product {

    private ProductBasicInfo basicInfo;
    private PricingInfo pricingInfo;
    private Size size;
    private ProductDetailsInfo productDetails;

    public Product(ProductBasicInfo basicInfo, PricingInfo pricingInfo, Size size, ProductDetailsInfo productDetails) {
        this.basicInfo = basicInfo;
        this.pricingInfo = pricingInfo;
        this.size = size;
        this.productDetails = productDetails;
    }

    public ProductBasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(ProductBasicInfo basicInfo) {
        this.basicInfo = basicInfo;
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
