package com.solvd.onlinemarket.enumeration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Planets {

    MERCURY("Mercury"),
    EARTH("Earth"),
    VENUS("Venus"),
    NEPTUNE("Neptune");

    private static final Logger logger = LogManager.getLogger(Planets.class);

    static {
        logger.info("Planets enum initialized with delivery capabilities.");
    }

    private final String name;

    Planets(String priorityType) {
        this.name = priorityType;
    }

    public String getName() {
        return name;
    }

    public String deliverPlanetTime(String productName) {
        String message = "Delivering " + productName + " to " + this.getName();
        logger.info(message);
        switch (this) {
            case MERCURY:
                logger.info("Estimated delivery time: 7 days to Mercury.");
                break;
            case EARTH:
                logger.info("Estimated delivery time: 1 day on Earth.");
                break;
            case VENUS:
                logger.info("Estimated delivery time: 5 days to Venus.");
                break;
            case NEPTUNE:
                logger.warn("Neptune delivery may face delays due to distance!");
                break;
            default:
                logger.error("Unknown planet for delivery!");
                throw new IllegalArgumentException("Unsupported planet: " + this.getName());
        }
        return message;
    }
}
