package employee;


import info.EmployeeInfo;

public class Manager extends Employee {

    private int teamSize;

    public Manager(EmployeeInfo employeesInfo) {
        super(employeesInfo);
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

}

