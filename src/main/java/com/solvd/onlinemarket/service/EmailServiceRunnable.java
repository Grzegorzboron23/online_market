package com.solvd.onlinemarket.service;

import com.solvd.onlinemarket.employee.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmailServiceRunnable implements Runnable {


    private static final Logger logger = LogManager.getLogger(EmailServiceRunnable.class);
    private final List<Person> people;
    private final String userType;

    public EmailServiceRunnable(List<Person> people, String userType) {
        this.people = people;
        this.userType = userType;
    }

    @Override
    public void run() {
        for (Person person : people) {
            String email = EmployeeService.generateEmail(person);
            logger.info("Sending email to {}:\n{}", userType, email);
        }
    }

}
