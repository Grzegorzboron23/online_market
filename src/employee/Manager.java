package employee;


import info.EmployeeInfo;

public class Manager extends Employee {

    private int teamSize;

    public Manager(int id, String name,
                   String surname, EmployeeInfo employeeInfo) {
        super(id, name, surname, employeeInfo);
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    @Override
    public String getRoleDescription() {
        return "Shift manager";
    }
}

