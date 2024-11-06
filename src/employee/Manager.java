package employee;


import info.AddressInfo;
import info.EmployeeInfo;

public class Manager extends Employee {

    private int teamSize;

    public Manager(EmployeeInfo employeesInfo, AddressInfo addressInfo, int teamSize) {
        super(employeesInfo, addressInfo);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

}

