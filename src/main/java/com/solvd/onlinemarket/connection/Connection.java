package com.solvd.onlinemarket.connection;

public class Connection {

    private final int id;

    public Connection(int id) {
        this.id = id;
    }

    public String getName() {
        try {
            // Simulate delay for database query
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Connection-" + id;
    }
}