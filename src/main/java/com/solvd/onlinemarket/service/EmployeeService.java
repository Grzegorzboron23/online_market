package com.solvd.onlinemarket.service;

import com.solvd.onlinemarket.employee.*;
import com.solvd.onlinemarket.exception.InvalidArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);

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

    //    All exception extends runtime assuming that there is an interface where employee can set this list
    //    Set to avoid get multiple times the same employee
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
                LOGGER.error("Skipping invalid employee or missing salary information {}", employee);
                continue;
            }

            if (employee instanceof CEO) {
//                Will log toString method
                LOGGER.warn("Skipping CEO: {}", employee);
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
                System.err.println("Skipping invalid employee  " + employee);
                continue;
            }

            String positionName = employee.getEmployeeInfo().getPosition().getName();
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
        LOGGER.info("Email generated for {}:\n", person.getName());
        LOGGER.info(emailMessage);
        LOGGER.info("Completed duties for: {}", person.getRoleDescription());
    }

    public static void changeSalary(List<Employee> employeeList, double randomValue) {
        Consumer<List<Employee>> raiseSalary = employees ->
                employees.forEach(employee -> {
                    BigDecimal currentSalary = employee.getEmployeeInfo().getSalary();
                    employee.getEmployeeInfo().setSalary(currentSalary.multiply(BigDecimal.valueOf(randomValue)));
                });

        Consumer<List<Employee>> logSalaries = employees ->
                employees.forEach(employee ->
                        LOGGER.info("Employee name and salary: {} - {}",
                                employee.getName(), employee.getEmployeeInfo().getSalary()));

        logSalaries.andThen(raiseSalary).andThen(logSalaries).accept(employeeList);
    }

    public static Function<List<Employee>, BigDecimal> getBiggestSalary() {
        return employees ->
                employees.stream()
                        .map(employee -> employee.getEmployeeInfo().getSalary())
                        .max(BigDecimal::compareTo)
                        .orElse(BigDecimal.ZERO);

    }

    public static <T, R> void processEmployees(List<T> employees, Function<T, R> action) {
        for (T employee : employees) {
            R result = action.apply(employee);
        }
    }
}

