package com.solvd.onlinemarket.enumeration;

public enum Category {

    ELECTRONICS("Electronics"),
    FOOD("Food"),
    BOOKS("Books");

    private final String name;

    Category(String displayName) {
        this.name = displayName;
    }

    public String getName() {
        return name;
    }
}

