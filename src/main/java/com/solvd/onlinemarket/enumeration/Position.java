package com.solvd.onlinemarket.enumeration;

public enum Position {

    CEO("CEO"),
    MANAGER("Manager"),
    CASHIER("Cashier");

    private final String name;

    Position(String positionName) {
        this.name = positionName;
    }

    public String getName() {
        return name;
    }
}
