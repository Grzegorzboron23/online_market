package com.solvd.enumeration;

public enum Category {

    ELECTRONICS("Electronics"),
    FOOD("Food"),
    BOOKS("Books");

    private final String categoryName;

    Category(String displayName) {
        this.categoryName = displayName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

