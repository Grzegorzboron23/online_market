import attribute.Size;
import employee.*;
import enumeration.Category;
import enumeration.Position;
import exception.FileProcessingException;
import exception.InvalidAddressException;
import exception.InvalidSalaryException;
import info.*;
import product.Book;
import product.FoodProduct;
import product.Laptop;
import product.Product;
import productInterface.Spoiled;
import profitCalculator.ProductCalculator;
import service.EmployeeService;
import service.OnlineMarket;
import service.ProductService;
import utils.CustomLinkedList;
import utils.FileReadUtil;
import utils.FileReadWithResourcesUtil;
import warehouse.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        List<Product> productList = Main.fillProductArray();
        List<Employee> employeeList = Main.fillEmployeeArray();

        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setProducts(productList);  // Setter used as product list may be modified later
        onlineMarket.setEmployees(employeeList); // Setter used as employee list may be modified later

//        System.out.println("Products: " + onlineMarket.getProducts());
//        System.out.println("Employees: " + onlineMarket.getEmployees());

//        System.out.println("Value of products: " + ProductService.countTotalValue(onlineMarket.getProducts()));

//        Main.demonstrateObjectMethodOverrides();
//        Main.demonstrateFinalStaticAndInterface();

//        Exceptions methods
//        try {
//            demonstrateExceptions();
//        } catch (RuntimeException e) {
//            System.err.println("Critical error : " + e.getMessage());
//            e.printStackTrace();
//            System.exit(1);
//        }
//        Main.getFileInfo();

        Main.demonstrateCollections();
        Main.demonstrateGenerics();
    }


    public static List<Employee> fillEmployeeArray() {
        EmployeeInfo employeeInfo = new EmployeeInfo(BigDecimal.valueOf(1000), LocalDate.now(), Position.CEO);

        // Setting addressInfo via setters as it is optional for Employee creation
        AddressInfo addressInfo = new AddressInfo();

        try {
            addressInfo.setCountry("Poland");
            addressInfo.setCity("Wroclaw");
            addressInfo.setStreet("Opolska");
        } catch (InvalidAddressException e) {
            System.out.println("Error " + e.getMessage());
        }

        CEO ceo = new CEO(123456789, "X", "XX", employeeInfo); // employeeInfo is essential for CEO creation, so it's in the constructor
        ceo.setAddressInfo(addressInfo); // addressInfo is optional, so it's set through a setter

        Manager manager = new Manager(123456789, "X", "XX", employeeInfo); // employeeInfo is essential, so it's in the constructor
        manager.setAddressInfo(addressInfo); // Optional addressInfo is set through a setter
        manager.setTeamSize(10); // teamSize can vary, so it's set via a setter

        Cashier cashier = new Cashier(123456789, "X", "XX", employeeInfo, 16); // employeeInfo is essential, passed through the constructor
        cashier.setAddressInfo(addressInfo); // Optional addressInfo is set through a setter

        return Arrays.asList(ceo, manager, cashier);
    }

    public static List<Product> fillProductArray() {
        ProductBasicInfo basicInfo = new ProductBasicInfo("X", Category.ELECTRONICS); // Essential information
        PricingInfo pricingInfo = new PricingInfo(BigDecimal.valueOf(10), 10); // Essential information
        Size size = new Size(30f, 20f, 1.5f); // Essential information

        // productDetailsInfo set via setters as it is optional information
        ProductDetailsInfo productDetailsInfo = new ProductDetailsInfo();
        productDetailsInfo.setDescription("X");
        productDetailsInfo.setIsAvailable(true);

        Book book = new Book(basicInfo, pricingInfo, size,
                "Author Name", 200, "Fiction", "233"); // Essential book details passed via constructor
        book.setProductDetails(productDetailsInfo); // Optional product details are set via setter
        book.setPublisher("publisher name"); // Publisher may be assigned later, so it's set via setter

        Laptop laptop = new Laptop(basicInfo, pricingInfo, size,
                "BrandName", "Intel i7", "Windows 10", 2.0, 5); // Essential laptop details passed via constructor
        laptop.setProductDetails(productDetailsInfo); // Optional product details set via setter

        FoodProduct foodProduct = new FoodProduct(basicInfo, pricingInfo, size,
                "OrganicBrand"); // Brand is essential, so passed via constructor
        foodProduct.setProductDetails(productDetailsInfo); // Optional product details set via setter
        foodProduct.setIsOrganic(true); // isOrganic is optional, set via setter

        return Arrays.asList(laptop, book, foodProduct);
    }

    public static void demonstrateObjectMethodOverrides() {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        Person person = new Employee(123, "X", "XX", employeeInfo);
        Person person2 = new Trainee(123, "X", "XX");
        Employee employee = new Employee(123, "X", "XX", employeeInfo);
        Cashier cashier = new Cashier(1235, "X", "XX", employeeInfo, 647);

//        toString method
        System.out.println("To string methods");
        System.out.println(employee); //toString result Person ... and default toString in employeeInfo like info.EmployeeInfo@...
        System.out.println(cashier);
        System.out.println(person);

//        equalsMethod
        System.out.println("Equals methods:");
        System.out.println(employee.equals(person)); //true because it compares only by id not by memory
        System.out.println(employee.equals(cashier)); //false because id is different

        // hashCode method
        System.out.println("hashCode output:");
        System.out.println("employee.hashCode(): " + employee.hashCode());
        System.out.println("person.hashCode(): " + person.hashCode());
        //person and employee has the same hashcode because equals compares only they id's
        System.out.println("cashier.hashCode(): " + cashier.hashCode());
//        different because of id


//        abstract method getRoleDescription
        System.out.println("Abstract method:");
        System.out.println(person.getRoleDescription()); //using getRoleDescription from employee because it's instantion of employee
        System.out.println(employee.getRoleDescription());
        System.out.println(cashier.getRoleDescription());  //using getRoleDescription from employee + adding more responsibilities
        System.out.println(person2.getRoleDescription());  //using getRoleDescription from trainee

//        Business method
        System.out.println("Business method:");
        EmployeeService.performDuties(person);
        EmployeeService.performDuties(person2);
        EmployeeService.performDuties(employee);
        EmployeeService.performDuties(cashier);


//       field of superclass
        System.out.println("Super class field");
        cashier.setSupervisor(person);
        System.out.println("Supervisor of manager (generalEmployee): " + cashier.getSupervisorDescription()); //get responsibilities from employee

        cashier.setSupervisor(person2);
        System.out.println("Supervisor of manager (seniorManager): " + cashier.getSupervisorDescription()); //get responsibilities from trainee

        System.out.println("Protected keyword");
        employee.showHowProtectedKeywordWorks(); //instead of using getId() I can use direct id field
    }

    public static void demonstrateFinalStaticAndInterface() {
        ProductBasicInfo basicInfo = new ProductBasicInfo("X", Category.ELECTRONICS); // Essential information
        PricingInfo pricingInfo = new PricingInfo(BigDecimal.valueOf(10), 10); // Essential information
        Size size = new Size(30f, 20f, 1.5f); // Essential information

        // productDetailsInfo set via setters as it is optional information
        ProductDetailsInfo productDetailsInfo = new ProductDetailsInfo();
        productDetailsInfo.setDescription("X");
        productDetailsInfo.setIsAvailable(true);

        Book book = new Book(basicInfo, pricingInfo, size,
                "Author Name", 200, "Fiction", "233"); // Essential book details passed via constructor
        book.setProductDetails(productDetailsInfo); // Optional product details are set via setter
        book.setPublisher("publisher name"); // Publisher may be assigned later, so it's set via setter

        Laptop laptop = new Laptop(basicInfo, pricingInfo, size,
                "BrandName", "Intel i7", "Windows 10", 2.0, 5); // Essential laptop details passed via constructor
        laptop.setProductDetails(productDetailsInfo); // Optional product details set via setter

        FoodProduct foodProduct = new FoodProduct(basicInfo, pricingInfo, size,
                "OrganicBrand"); // Brand is essential, so passed via constructor
        foodProduct.setProductDetails(productDetailsInfo); // Optional product details set via setter
        foodProduct.setIsOrganic(true); // isOrganic is optional, set via setter


//        Static and final keyword
        System.out.println("Product calculator ");
        System.out.println("Calculate Profit " + ProductCalculator.calculateProfit(
                laptop.getPricingInfo().getPrice(),
                Laptop.getProductionCost()));
        System.out.println("Get available discount " + ProductCalculator.getAvailableDiscount());

        System.out.println("Final method");
        System.out.println("Calculate Surface Area " + laptop.calculateSurfaceArea());

//        Interfaces
        System.out.println("ISBN number " + book.isValidISBN());
        System.out.println("ISBN number " + book.getISBN());

        System.out.println("Interface check method laptop lightweight" + laptop.isLightweight());

        System.out.println("Inteface as field in class");
        Warehouse warehouse = new Warehouse(foodProduct);
        warehouse.checkProductStatus();

        System.out.println("Inteface as parameter in method ");
        checkSpoilage(foodProduct);

        System.out.println("Collection ");
        checkAllSpoiled(List.of(foodProduct));

    }

    public static void demonstrateExceptions() {
        EmployeeInfo employeeInfo = new EmployeeInfo(BigDecimal.valueOf(1000), LocalDate.now(), Position.CEO);
        AddressInfo addressInfo = new AddressInfo();

        handleAddressSetting(addressInfo);

        try {
            // Unchecked exception assuming we use calculator in our program to calculate salary
            // don't need to be surrounded by try catch block
            employeeInfo.setSalary(BigDecimal.valueOf(-1L));
        } catch (InvalidSalaryException e) {
            System.err.println("Error while setting salary: " + e.getMessage());
        }

        Size size = new Size(10f, 10f, 10f);
        try {
            //Unchecked  Assuming program calculates height of a product after some accident
            // don't need to be surrounded by try catch block
            size.setHeight(-10f);
        } catch (IllegalArgumentException e) {
            System.err.println("Error while setting height: " + e.getMessage());
        }

        try {
            // Unchecked exception assuming the ID number is auto-created by a database
            // don't need to be surrounded by try catch block
            Cashier cashier = new Cashier(-123, "X", "XX", employeeInfo, 647);
        } catch (IllegalArgumentException e) {
            System.err.println("Error while creating cashier: " + e.getMessage());
            throw new RuntimeException("Critical error by creating cashier", e);
        }
    }

    public static void demonstrateCollections() {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        Employee employee = new Employee(123, "X", "XX", employeeInfo);
        employee.getEmployeeInfo().setSalary(BigDecimal.valueOf(36500));
        employee.getEmployeeInfo().setPosition(Position.CASHIER);

        EmployeeInfo employeeInfo2 = new EmployeeInfo();
        Employee employee2 = new Employee(1237, "X", "XXXX", employeeInfo2);
        employee2.getEmployeeInfo().setSalary(BigDecimal.valueOf(38500));
        employee2.getEmployeeInfo().setPosition(Position.MANAGER);

        EmployeeInfo employeeInfo3 = new EmployeeInfo();
        Cashier cashier = new Cashier(1235, "X", "XXXXXXX", employeeInfo3, 647);
        cashier.getEmployeeInfo().setSalary(BigDecimal.valueOf(8700));
        cashier.getEmployeeInfo().setPosition(Position.CASHIER);

        System.out.println("EmployeeService Set");
        System.out.println("Savings " +
                EmployeeService.decreasePaidForEmployeesAndCalculateSavings(
                        Set.of(employee, cashier), 5)
        );

        System.out.println("Product Service List");
        System.out.println("Needed space " +
                ProductService.countTotalSpaceForProductsInWareHouse(Main.setProductList()));

        System.out.println("HashMap");
        System.out.println("Group employees");
        Map<String, List<Employee>> mapOfGroupedEmployees = EmployeeService.groupEmployeesByPosition(List.of(employee, employee2, cashier));

        for (String position : mapOfGroupedEmployees.keySet()) {
            System.out.println("Position " + position + " " + mapOfGroupedEmployees.get(position));
        }

        System.out.println("Most paid employee " + EmployeeService.findMostPaidEmployee(Set.of(employee, employee2, cashier)));

        System.out.println("Product Categories by Size:");
        Map<String, List<Product>> productCategories = ProductService.categorizeProductsBySize(Main.setProductList());
        for (Map.Entry<String, List<Product>> entry : productCategories.entrySet()) {
            System.out.println("Category: " + entry.getKey() + " - Products: " + entry.getValue());
        }
    }

    public static void demonstrateGenerics() {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        Employee employee = new Employee(123, "X", "XX", employeeInfo);
        employee.getEmployeeInfo().setSalary(BigDecimal.valueOf(36500));
        employee.getEmployeeInfo().setPosition(Position.CASHIER);

        EmployeeInfo employeeInfo2 = new EmployeeInfo();
        Employee employee2 = new Employee(1237, "X", "XXXX", employeeInfo2);
        employee2.getEmployeeInfo().setSalary(BigDecimal.valueOf(38500));
        employee2.getEmployeeInfo().setPosition(Position.MANAGER);

        EmployeeInfo employeeInfo3 = new EmployeeInfo();
        Cashier cashier = new Cashier(1235, "X", "XXXXXXX", employeeInfo3, 647);
        cashier.getEmployeeInfo().setSalary(BigDecimal.valueOf(8700));
        cashier.getEmployeeInfo().setPosition(Position.CASHIER);


        CustomLinkedList<Employee> customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(employee);
        customLinkedList.add(employee2);


        System.out.println("First employee " + customLinkedList.get(0));

//Add element to specified index
        customLinkedList.add(0, cashier);
        System.out.println("First employee " + customLinkedList.get(0));

//        Remove element
        customLinkedList.remove(cashier);
        System.out.println("First employee after remove " + customLinkedList.get(0));
        customLinkedList.add(0, cashier);


        System.out.println("Does list conatains 'Cashier'? " + customLinkedList.contains(cashier));

        // Get index element
        System.out.println("'Cashier' index : " + customLinkedList.indexOf(cashier));

//        foreach loop
        for (Employee employee3 : customLinkedList) {
            System.out.println("Employee in loop " + employee3.getName());
        }

        customLinkedList.clear();
        System.out.println("After clear : " + customLinkedList.indexOf(cashier));

    }

    private static void handleAddressSetting(AddressInfo addressInfo) {
        try {
            addressInfo.setStreet("Street1");
            addressInfo.setCountry("P"); // This will throw an exception
            addressInfo.setCity("Wroclaw");
        } catch (InvalidAddressException e) {
            System.out.println("Error while setting address in helper method: " + e.getMessage());
        }
    }


    public static void getFileInfo() {
        String filePath = "example.txt";
        try {
            List<String> fileContents = FileReadWithResourcesUtil.getResourcesFromFile(filePath);
            fileContents.forEach(System.out::println);
        } catch (FileProcessingException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        try {
            List<String> fileContents = FileReadUtil.getResourcesFromFile(filePath);
            fileContents.forEach(System.out::println);
        } catch (FileProcessingException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }

    public static void checkSpoilage(Spoiled item) {
        if (item.isSpoiled()) {
            System.out.println("The product is spoiled.");
        } else {
            System.out.println("The product is still good.");
        }
    }

    public static void checkAllSpoiled(List<Spoiled> items) {
        for (Spoiled item : items) {
            System.out.println("Checking product...");
            if (item.isSpoiled()) {
                System.out.println("The product is spoiled.");
            } else {
                System.out.println("The product is in good condition.");
            }
        }
    }

    public static List<Product> setProductList() {
        ProductBasicInfo basicInfo = new ProductBasicInfo("X", Category.ELECTRONICS); // Essential information
        PricingInfo pricingInfo = new PricingInfo(BigDecimal.valueOf(10), 10); // Essential information
        Size size = new Size(30f, 20f, 1.5f); // Essential information

        // productDetailsInfo set via setters as it is optional information
        ProductDetailsInfo productDetailsInfo = new ProductDetailsInfo();
        productDetailsInfo.setDescription("X");
        productDetailsInfo.setIsAvailable(true);

        Book book = new Book(basicInfo, pricingInfo, size,
                "Author Name", 200, "Fiction", "233"); // Essential book details passed via constructor
        book.setProductDetails(productDetailsInfo); // Optional product details are set via setter
        book.setPublisher("publisher name"); // Publisher may be assigned later, so it's set via setter

        Laptop laptop = new Laptop(basicInfo, pricingInfo, size,
                "BrandName", "Intel i7", "Windows 10", 2.0, 5); // Essential laptop details passed via constructor
        laptop.setProductDetails(productDetailsInfo); // Optional product details set via setter

        FoodProduct foodProduct = new FoodProduct(basicInfo, pricingInfo, size,
                "OrganicBrand"); // Brand is essential, so passed via constructor
        foodProduct.setProductDetails(productDetailsInfo); // Optional product details set via setter
        foodProduct.setIsOrganic(true); // isOrganic is optional, set via setter


        return Arrays.asList(book, laptop, foodProduct);
    }
}
