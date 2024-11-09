package employee;


import info.EmployeeInfo;

import java.util.Objects;

public class Cashier extends Employee {
    private Person supervisor;
    private final int registerNumber;

    public Cashier(int id, String name,
                   String surname, EmployeeInfo employeeInfo,
                   int registerNumber) {
        super(id,name,surname,employeeInfo);
        this.registerNumber = registerNumber;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }


//    Just to show how use it
    @Override
    public String getRoleDescription() {
        return super.getRoleDescription() + " responsible for cash";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cashier cashier)) return false;
        if (!super.equals(o)) return false;
        return registerNumber == cashier.registerNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registerNumber);
    }

    @Override
    public String toString() {
        return super.toString() + " Cashier{" +
                "registerNumber=" + registerNumber +
                '}';
    }

    public void setSupervisor(Person supervisor) {
        this.supervisor = supervisor;
    }

    public Person getSupervisor() {
        return supervisor;
    }

    public String getSupervisorDescription() {
        return supervisor != null ? supervisor.getRoleDescription() : "No supervisor assigned";
    }
}
