package service;

import employee.*;

public class EmployeeService {
    public static void performDuties(Person person) {
         if (person instanceof Cashier) {
            Cashier cashier = (Cashier) person;
            System.out.println("Cashier's register number: " + cashier.getRegisterNumber());
            cashier.getRoleDescription();
        } else if (person instanceof Trainee) {
             Trainee trainee = (Trainee) person;
            System.out.println("Make coffee");
        } else if (person instanceof Employee) {
            Employee employee = (Employee) person;
            System.out.println("Set the project");
        } else {
            System.out.println("Unrecognized role.");
        }
        System.out.println("Completed duties for: " + person.getRoleDescription());
    }
}
