package com.solvd.warehouse;


import com.solvd.productInterface.Spoiled;

public class Warehouse {

    private Spoiled product;

    public Warehouse(Spoiled product) {
        this.product = product;
    }

    public void checkProductStatus() {
        if (product.isSpoiled()) {
            System.out.println("The com.solvd.product in the com.solvd.warehouse is spoiled.");
        } else {
            System.out.println("The com.solvd.product in the com.solvd.warehouse is in good condition.");
        }
    }
}
