package employee;


import info.EmployeeInfo;

public class Cashier extends Employee {

    private final int registerNumber;

    public Cashier(EmployeeInfo employeesInfo, int registerNumber) {
        super(employeesInfo);
        this.registerNumber = registerNumber;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }
}
