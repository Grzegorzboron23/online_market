package enumeration;

public enum Category {

    ELECTRONICS("Electronics"),
    FOOD("Food"),
    CLOTHING("Clothing"),
    HOME_APPLIANCES("Home Appliances"),
    TOYS("Toys"),
    BEAUTY("Beauty"),
    BOOKS("Books"),
    SPORTS("Sports");

    private final String categoryName;

    Category(String displayName) {
        this.categoryName = displayName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

