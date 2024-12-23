package com.solvd.onlinemarket.warehouse;


import com.solvd.onlinemarket.productInterface.Spoiled;

public class Warehouse {

    private Spoiled product;

    public Warehouse(Spoiled product) {
        this.product = product;
    }

    public void checkProductStatus() {
        if (product.isSpoiled()) {
            System.out.println("The product in the com.solvd.onlinemarket.warehouse is spoiled.");
        } else {
            System.out.println("The com.solvd.onlinemarket.product in the com.solvd.onlinemarket.warehouse is in good condition.");
        }
    }
}
