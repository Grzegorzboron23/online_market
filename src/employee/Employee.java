package employee;


import info.AddressInfo;
import info.EmployeeInfo;

public class Employee {

    private EmployeeInfo employeesInfo;
    private AddressInfo addressInfo;

    public Employee(EmployeeInfo employeesInfo) {
        this.employeesInfo = employeesInfo;
    }

    public EmployeeInfo getEmployeesInfo() {
        return employeesInfo;
    }

    public void setEmployeesInfo(EmployeeInfo employeesInfo) {
        this.employeesInfo = employeesInfo;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }
}

