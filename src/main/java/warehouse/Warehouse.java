package warehouse;


import productInterface.Spoiled;

public class Warehouse {

    private Spoiled product;

    public Warehouse(Spoiled product) {
        this.product = product;
    }

    public void checkProductStatus() {
        if (product.isSpoiled()) {
            System.out.println("The product in the warehouse is spoiled.");
        } else {
            System.out.println("The product in the warehouse is in good condition.");
        }
    }
}
