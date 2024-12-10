package com.solvd.onlinemarket.enumeration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PriorityType {
    
    HIGH("High priority deliver as fast as possible"),
    MEDIUM("Medium priority deliver in 2 days"),
    LOW("Low priority deliver in 5 days");

    private static final Logger logger = LogManager.getLogger(PriorityType.class);

    static {
        logger.info("Using PriorityType enum check if product was delivered");
    }

    private final String priorityType;

    PriorityType(String priorityType) {
        this.priorityType = priorityType;
    }

    public String getPriorityType() {
        return priorityType;
    }

    public String messageToSupplier() {
        return "Dear Supplier \n this product is " + this.getPriorityType() +
                "\n Best Regards";
    }
}
