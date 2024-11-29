package com.solvd.onlinemarket.info;


import com.solvd.onlinemarket.enumeration.Category;

public class ProductBasicInfo {

    private String name;
    private Category category;

    public ProductBasicInfo(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public ProductBasicInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

