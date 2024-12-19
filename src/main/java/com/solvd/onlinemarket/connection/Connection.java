package com.solvd.onlinemarket.connection;

public class Connection {

    private final int id;

    public Connection(int id) {
        this.id = id;
    }

    public String getName() {
        return "Connection-" + id;
    }
}