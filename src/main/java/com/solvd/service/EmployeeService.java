package com.solvd.service;

import com.solvd.employee.*;
import com.solvd.exception.InvalidArgumentException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

    //    All com.solvd.exception extends runtime assuming that there is an interface where com.solvd.employee can set this list
    //    Set to avoid get multiple times the same com.solvd.employee
    public static BigDecimal decreasePaidForEmployeesAndCalculateSavings(Set<Employee> employees, Integer percentage) {
        if (employees == null || employees.isEmpty()) {
            throw new InvalidArgumentException("Provide some employees");
        }

        if (percentage == null || percentage <= 0 || percentage > 100) {
            throw new InvalidArgumentException("Percentage should be between 1 and 100");
        }

        BigDecimal savings = BigDecimal.ZERO;
        for (Employee employee : employees) {
            if (employee == null
                    || employee.getEmployeeInfo() == null
                    || employee.getEmployeeInfo().getSalary() == null) {
                System.err.println("Skipping invalid com.solvd.employee or missing salary information " + employee);
                continue;
            }

            if (employee instanceof CEO) {
//                Will log toString method
                System.out.println("Skipping CEO: " + employee);
                continue;
            }

            BigDecimal currentSalary = employee.getEmployeeInfo().getSalary();

            BigDecimal reduction = currentSalary.multiply(BigDecimal.valueOf(percentage))
                    .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

            BigDecimal newSalary = currentSalary.subtract(reduction);
            employee.getEmployeeInfo().setSalary(newSalary);

            savings = savings.add(reduction);
        }

        return savings;
    }

    public static Map<String, List<Employee>> groupEmployeesByPosition(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new InvalidArgumentException("Provide some employees");
        }

        Map<String, List<Employee>> result = new HashMap<>();

        for (Employee employee : employees) {
            if (employee == null
                    || employee.getEmployeeInfo() == null
                    || employee.getEmployeeInfo().getPosition() == null) {
                System.err.println("Skipping invalid com.solvd.employee  " + employee);
                continue;
            }

            String positionName = employee.getEmployeeInfo().getPosition().getPositionName();
//            ArrayList because I add element at the end and iterate throught it in main method
//            I would use LinkedList if I would delete elements or add elements in the middle of array
            result.computeIfAbsent(positionName, key -> new ArrayList<>()).add(employee);
        }

        return result;
    }

    public static Optional<Employee> findMostPaidEmployee(Set<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new InvalidArgumentException("Provide some employees");
        }

        return employees.stream()
                .filter(e -> e.getEmployeeInfo() != null && e.getEmployeeInfo().getSalary() != null)
                .max(Comparator.comparing(e -> e.getEmployeeInfo().getSalary()));
    }


    public static void performDuties(Person person) {
        String emailMessage = generateEmail(person);
        System.out.println("Email generated for " + person.getName() + ":\n");
        System.out.println(emailMessage);
        System.out.println("Completed duties for: " + person.getRoleDescription());
    }
}

