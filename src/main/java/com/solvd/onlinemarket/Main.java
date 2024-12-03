package com.solvd.onlinemarket;

import com.solvd.onlinemarket.attribute.Size;
import com.solvd.onlinemarket.employee.*;
import com.solvd.onlinemarket.enumeration.Category;
import com.solvd.onlinemarket.enumeration.Position;
import com.solvd.onlinemarket.exception.FileProcessingException;
import com.solvd.onlinemarket.exception.InvalidAddressException;
import com.solvd.onlinemarket.exception.InvalidSalaryException;
import com.solvd.onlinemarket.info.*;
import com.solvd.onlinemarket.product.Book;
import com.solvd.onlinemarket.product.FoodProduct;
import com.solvd.onlinemarket.product.Laptop;
import com.solvd.onlinemarket.product.Product;
import com.solvd.onlinemarket.productInterface.Spoiled;
import com.solvd.onlinemarket.profitCalculator.ProductCalculator;
import com.solvd.onlinemarket.service.BookService;
import com.solvd.onlinemarket.service.EmployeeService;
import com.solvd.onlinemarket.service.ProductService;
import com.solvd.onlinemarket.utils.CustomLinkedList;
import com.solvd.onlinemarket.utils.FileReadUtil;
import com.solvd.onlinemarket.utils.FileReadWithResourcesUtil;
import com.solvd.onlinemarket.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        logger.debug("Start program");

//        List<Product> productList = Main.fillProductArray();
//        List<Employee> employeeList = Main.fillEmployeeArray();

//        OnlineMarket onlineMarket = new OnlineMarket();
//        onlineMarket.setProducts(productList);
        // Setter used as product list may be modified later
//        onlineMarket.setEmployees(employeeList);
        // Setter used as employee list may be modified later

//        logger.info("Products: {}", onlineMarket.getProducts());
//        logger.info("Employees: {}", onlineMarket.getEmployees());

//        logger.info("Value of products: {}", ProductService.countTotalValue(onlineMarket.getProducts()));

//        Main.demonstrateObjectMethodOverrides();
//        Main.demonstrateFinalStaticAndInterface();

//        Exceptions methods
//        try {
//            demonstrateExceptions();
//        } catch (RuntimeException e) {
//            logger.error("Critical error : ", e.getMessage());
//            e.printStackTrace();
//            System.exit(1);
//        }
//        Main.getFileInfo();

//        Main.demonstrateCollections();
//        Main.demonstrateGenerics();

        Main.demonstrateApacheLibraries();
    }

    private static void demonstrateApacheLibraries() {
        try {
            URL inputResource = Main.class.getResource("/bookForKids.txt");
            String inputFilePath = new File(inputResource.toURI()).getAbsolutePath();

            String outputFilePath = "src/main/resources/output.txt";
            int uniqueWordCount = BookService.countWordsInBook(inputFilePath, outputFilePath);

            logger.info("Number of unique words: {}", uniqueWordCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<Employee> fillEmployeeArray() {
        EmployeeInfo employeeInfo = new EmployeeInfo(BigDecimal.valueOf(1000), LocalDate.now(), Position.CEO);

        // Setting addressInfo via setters as it is optional for Employee creation
        AddressInfo addressInfo = new AddressInfo();

        try {
            addressInfo.setCountry("Po");
            addressInfo.setCity("Wroclaw");
            addressInfo.setStreet("Opolska");
        } catch (InvalidAddressException e) {
            logger.error("Error ", e);
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
        logger.info("To string methods");
        logger.info(employee); //toString result Person ... and default toString in employeeInfo like info.EmployeeInfo@...
        logger.info(cashier);
        logger.info(person);

//        equalsMethod
        logger.info("Equals methods:");
        logger.info(employee.equals(person)); //true because it compares only by id not by memory
        logger.info(employee.equals(cashier)); //false because id is different

        // hashCode method
        logger.info("hashCode output:");
        logger.info("employee.hashCode(): {}", employee.hashCode());
        logger.info("person.hashCode(): {}", person.hashCode());
        //person and .employee has the same hashcode because equals compares only they id's
        logger.info("cashier.hashCode(): {}", cashier.hashCode());
//        different because of id


//        abstract method getRoleDescription
        logger.info("Abstract method:");
        logger.info(person.getRoleDescription()); //using getRoleDescription from employee because it's instantion of employee
        logger.info(employee.getRoleDescription());
        logger.info(cashier.getRoleDescription());  //using getRoleDescription from employee + adding more responsibilities
        logger.info(person2.getRoleDescription());  //using getRoleDescription from trainee

//        Business method
        logger.info("Business method:");
        EmployeeService.performDuties(person);
        EmployeeService.performDuties(person2);
        EmployeeService.performDuties(employee);
        EmployeeService.performDuties(cashier);


//       field of superclass
        logger.info("Super class field");
        cashier.setSupervisor(person);
        logger.info("Supervisor of manager (generalEmployee): {}", cashier.getSupervisorDescription()); //get responsibilities from employee

        cashier.setSupervisor(person2);
        logger.info("Supervisor of manager (seniorManager): {}", cashier.getSupervisorDescription()); //get responsibilities from trainee

        logger.info("Protected keyword");
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
        logger.info("Product calculator ");
        logger.info("Calculate Profit {}", ProductCalculator.calculateProfit(
                laptop.getPricingInfo().getPrice(),
                Laptop.getProductionCost()));
        logger.info("Get available discount {}", ProductCalculator.getAvailableDiscount());

        logger.info("Final method");
        logger.info("Calculate Surface Area {}", laptop.calculateSurfaceArea());

//        Interfaces
        logger.info("ISBN number {}", book.isValidISBN());
        logger.info("ISBN number {}", book.getISBN());

        logger.info("Interface check method laptop lightweight{}", laptop.isLightweight());

        logger.info("Inteface as field in class");
        Warehouse warehouse = new Warehouse(foodProduct);
        warehouse.checkProductStatus();

        logger.info("Inteface as parameter in method ");
        checkSpoilage(foodProduct);

        logger.info("Collection ");
        checkAllSpoiled(List.of(foodProduct));

    }

    public static void demonstrateExceptions() {
        EmployeeInfo employeeInfo = new EmployeeInfo(BigDecimal.valueOf(1000), LocalDate.now(), Position.CEO);
        AddressInfo addressInfo = new AddressInfo();

        handleAddressSetting(addressInfo);

        // Checked exception assuming we manually set salary
        // don't need to be surrounded by try catch block
        try {
            employeeInfo.setSalary(BigDecimal.valueOf(-1L));
        } catch (InvalidSalaryException e) {
            logger.warn("Error while setting salary: ", e);
        }


        Size size = new Size(10f, 10f, 10f);
        try {
            //Unchecked  Assuming user provides height of a product after some accident
            // don't need to be surrounded by try catch block
            size.setHeight(-10f);
        } catch (IllegalArgumentException e) {
            logger.warn("Error while setting height: ", e);
        }

        try {
            // Checked exception assuming the ID number is auto-created by a database
            // don't need to be surrounded by try catch block
            Cashier cashier = new Cashier(-123, "X", "XX", employeeInfo, 647);
        } catch (IllegalArgumentException e) {
            logger.error("Error while creating cashier: ", e);
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

        logger.info("EmployeeService Set");
        logger.info("Savings {}", EmployeeService.decreasePaidForEmployeesAndCalculateSavings(
                Set.of(employee, cashier), 5));

        logger.info("Product Service List");
        logger.info("Needed space {}", ProductService.countTotalSpaceForProductsInWareHouse(Main.setProductList()));

        logger.info("HashMap");
        logger.info("Group employees");
        Map<String, List<Employee>> mapOfGroupedEmployees = EmployeeService.groupEmployeesByPosition(List.of(employee, employee2, cashier));

        for (String position : mapOfGroupedEmployees.keySet()) {
            logger.info("Position {} {}", position, mapOfGroupedEmployees.get(position));
        }

        logger.info("Most paid employee {}", EmployeeService.findMostPaidEmployee(Set.of(employee, employee2, cashier)));

        logger.info("Product Categories by Size:");
        Map<String, List<Product>> productCategories = ProductService.categorizeProductsBySize(Main.setProductList());
        for (Map.Entry<String, List<Product>> entry : productCategories.entrySet()) {
            logger.info("Category: {} - Products: {}", entry.getKey(), entry.getValue());
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


        logger.info("First employee {}", customLinkedList.get(0));

//Add element to specified index
        customLinkedList.add(0, cashier);
        logger.info("First employee {}", customLinkedList.get(0));

//        Remove element
        customLinkedList.remove(cashier);
        logger.info("First employee after remove {}", customLinkedList.get(0));
        customLinkedList.add(0, cashier);


        logger.info("Does list contains 'Cashier'? {}", customLinkedList.contains(cashier));

        // Get index element
        logger.info("'Cashier' index : {}", customLinkedList.indexOf(cashier));

//        foreach loop
        for (Employee employee3 : customLinkedList) {
            logger.info("Employee in loop {}", employee3.getName());
        }

        customLinkedList.clear();
        logger.info("After clear : {}", customLinkedList.indexOf(cashier));

    }

    private static void handleAddressSetting(AddressInfo addressInfo) {
        try {
            addressInfo.setStreet("Street1");
            addressInfo.setCountry("P"); // This will throw an exception
            addressInfo.setCity("Wroclaw");
        } catch (InvalidAddressException e) {
            logger.error("Error while setting address in helper method", e);
        }
    }


    public static void getFileInfo() {
        String filePath = "example.txt";
        try {
            List<String> fileContents = FileReadWithResourcesUtil.getResourcesFromFile(filePath);
            fileContents.forEach(System.out::println);
        } catch (FileProcessingException e) {
            logger.error("An error occurred:", e);
        }

        try {
            List<String> fileContents = FileReadUtil.getResourcesFromFile(filePath);
            fileContents.forEach(System.out::println);
        } catch (FileProcessingException e) {
            logger.error("An error occurred: ", e);
        }

    }

    public static void checkSpoilage(Spoiled item) {
        if (item.isSpoiled()) {
            logger.warn("The product is spoiled");
        } else {
            logger.info("The product is still good.");
        }
    }

    public static void checkAllSpoiled(List<Spoiled> items) {
        for (Spoiled item : items) {
            logger.info("Checking product...");
            if (item.isSpoiled()) {
                logger.warn("The product is spoiled.");
            } else {
                logger.info("The product is in good condition.");
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