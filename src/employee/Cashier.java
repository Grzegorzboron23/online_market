package employee;


import info.AddressInfo;
import info.EmployeeInfo;

public class Cashier extends Employee {

    private int registerNumber;

    public Cashier(EmployeeInfo employeesInfo, AddressInfo addressInfo, int registerNumber) {
        super(employeesInfo, addressInfo);
        this.registerNumber = registerNumber;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

}
