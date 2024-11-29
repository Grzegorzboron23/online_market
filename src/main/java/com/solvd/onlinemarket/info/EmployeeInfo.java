package com.solvd.onlinemarket.info;


import com.solvd.onlinemarket.enumeration.Position;
import com.solvd.onlinemarket.exception.InvalidSalaryException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeInfo {

    private BigDecimal salary;
    private LocalDate hireDate;
    private Position position;

    public EmployeeInfo(BigDecimal salary, LocalDate hireDate, Position position) {
        this.salary = salary;
        this.hireDate = hireDate;
        this.position = position;
    }

    public EmployeeInfo() {
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidSalaryException("Salary must not be null or negative");
        }
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
