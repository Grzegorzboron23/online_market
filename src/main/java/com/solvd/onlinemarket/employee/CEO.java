package com.solvd.onlinemarket.employee;


import com.solvd.onlinemarket.info.EmployeeInfo;

public class CEO extends Employee {

    public CEO(int id, String name,
               String surname, EmployeeInfo employeesInfo) {
        super(id, name, surname, employeesInfo);
    }

    @Override
    public String getRoleDescription() {
        return "Chief responsible for whole Online Market";
    }
}
