package employee;

public class Trainee extends Person{
    public Trainee(int id, String name, String surname) {
        super(id, name, surname);
    }

    @Override
    public String getRoleDescription() {
        return "No responsibilities just make coffee";
    }
}
