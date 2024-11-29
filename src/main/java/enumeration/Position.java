package enumeration;

public enum Position {

    CEO("CEO"),
    MANAGER("Manager"),
    CASHIER("Cashier");

    private final String positionName;

    Position(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }
}
