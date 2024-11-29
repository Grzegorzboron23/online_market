package com.solvd.onlinemarket.employee;


import com.solvd.onlinemarket.info.AddressInfo;
import com.solvd.onlinemarket.info.EmployeeInfo;

import java.util.Objects;

public class Employee extends Person {

    private EmployeeInfo employeeInfo;
    private AddressInfo addressInfo;

    public Employee(int id, String name, String surname, EmployeeInfo employeesInfo) {
        super(id, surname, name);
        this.employeeInfo = employeesInfo;
    }

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    @Override
    public String getRoleDescription() {
        return "General com.solvd.onlinemarket.employee with standard responsibilities.";
    }

    @Override
    public String toString() {
        return super.toString() + " Employee{" +
                "employeesInfo=" + employeeInfo +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId();
    }

    public void showHowProtectedKeywordWorks() {
        System.out.println("Id " + id);
        System.out.println("Name " + name);
        System.out.println("Surname " + surname);
        System.out.println("Phone " + phoneNumber);
        System.out.println("Email " + email);
    }
}

