package service;

import employee.Cashier;
import employee.Employee;
import employee.Person;
import employee.Trainee;

public class EmployeeService {

    public static String generateEmail(Person person) {
        StringBuilder emailMessage = new StringBuilder();

        emailMessage.append("Dear ").append(person.getName()).append(",\n\n");

        if (person instanceof Cashier cashier) {
            emailMessage.append("As a Cashier, please ensure that your register (number ")
                    .append(cashier.getRegisterNumber())
                    .append(") is prepared and that you handle all transactions accurately.\n");
        } else if (person instanceof Trainee) {
            emailMessage.append("As a Trainee, please assist in daily tasks, including making coffee and observing team operations.\n");
        } else if (person instanceof Employee) {
            emailMessage.append("As an Employee, please focus on setting up your assigned projects and completing your tasks promptly.\n");
        } else {
            emailMessage.append("Your role is currently unrecognized. Please reach out to HR for clarification on your duties.\n");
        }

        emailMessage.append("\nBest regards,\nManagement");

        return emailMessage.toString();
    }

    public static void performDuties(Person person) {
        String emailMessage = generateEmail(person);
        System.out.println("Email generated for " + person.getName() + ":\n");
        System.out.println(emailMessage);
        System.out.println("Completed duties for: " + person.getRoleDescription());
    }
}

