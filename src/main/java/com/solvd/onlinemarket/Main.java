package com.solvd.onlinemarket;

import com.solvd.onlinemarket.attribute.Size;
import com.solvd.onlinemarket.connection.Connection;
import com.solvd.onlinemarket.connection.ConnectionPool;
import com.solvd.onlinemarket.employee.*;
import com.solvd.onlinemarket.employeeInterface.functionalInterface.CustomLambda;
import com.solvd.onlinemarket.enumeration.*;
import com.solvd.onlinemarket.exception.FileProcessingException;
import com.solvd.onlinemarket.exception.InvalidAddressException;
import com.solvd.onlinemarket.exception.InvalidArgumentException;
import com.solvd.onlinemarket.exception.InvalidSalaryException;
import com.solvd.onlinemarket.info.*;
import com.solvd.onlinemarket.product.Book;
import com.solvd.onlinemarket.product.FoodProduct;
import com.solvd.onlinemarket.product.Laptop;
import com.solvd.onlinemarket.product.Product;
import com.solvd.onlinemarket.productInterface.Spoiled;
import com.solvd.onlinemarket.profitCalculator.ProductCalculator;
import com.solvd.onlinemarket.service.*;
import com.solvd.onlinemarket.utils.*;
import com.solvd.onlinemarket.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.solvd.onlinemarket.utils.StreamUtils.findProductNameByCategory;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        LOGGER.debug("Start program");

//        List<Product> productList = Main.fillProductArray();
//        List<Employee> employeeList = Main.fillEmployeeArray();

//        OnlineMarket onlineMarket = new OnlineMarket();
//        onlineMarket.setProducts(productList);
        // Setter used as product list may be modified later
//        onlineMarket.setEmployees(employeeList);
        // Setter used as employee list may be modified later

//        LOGGER.info("Products: {}", onlineMarket.getProducts());
//        LOGGER.info("Employees: {}", onlineMarket.getEmployees());

//        LOGGER.info("Value of products: {}", ProductService.countTotalValue(onlineMarket.getProducts()));

//        Main.demonstrateObjectMethodOverrides();
//        Main.demonstrateFinalStaticAndInterface();

//        Exceptions methods
//        try {
//            demonstrateExceptions();
//        } catch (RuntimeException e) {
//            LOGGER.error("Critical error : ", e.getMessage());
//            e.printStackTrace();
//            System.exit(1);
//        }
//        Main.getFileInfo();

//        Main.demonstrateCollections();
//        Main.demonstrateGenerics();

//        Main.demonstrateApacheLibraries();

//        Main.demonstrateEnums();
//        Main.demonstrateLambdas();
//        Main.demonstrateCustomLambda();
//        Main.demonstrateStreamAPI();
//        Main.demonstrateReflection();
//        Main.demonstrateCreateObjectUsingReflection();
        Main.demonstrateThreads();
        Main.demonstrateCompletableFuture();
        Main.demonstrateConnectionPool();
    }


    public static void demonstrateThreads() {


        // Runnable-based thread for cashiers
        Thread cashierThread = new Thread(new EmailServiceRunnable(fillPersonArray(), "Cashier"));
        cashierThread.start();

        // Thread-based class for other users
        EmailServiceThread traineeEmployeeThread = new EmailServiceThread(fillPersonArray(), "Trainee/Employee");
        traineeEmployeeThread.start();

        // Wait for both threads to finish
        try {
            cashierThread.join();
            traineeEmployeeThread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted: {}", e.getMessage());
        }

        LOGGER.info("\nAll emails have been sent successfully.");
    }

    public static void demonstrateCompletableFuture() {
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

        CompletableFuture<BigDecimal> decreasePays = CompletableFuture.supplyAsync(() -> {
            return EmployeeService.decreasePaidForEmployeesAndCalculateSavings(Set.of(employee, cashier), 10);
        });

        try {
            LOGGER.info("Calculate savings  in different thread {}", decreasePays.get());
        } catch (Exception e) {
            LOGGER.error("Error decreasePays method", e);
        }
    }

    public static void demonstrateConnectionPool() {
        final int POOL_SIZE = 5;
        final int THREAD_COUNT = 7;

        ConnectionPool connectionPool = new ConnectionPool(POOL_SIZE);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        Runnable task = () -> {
            try {
                Connection connection = connectionPool.acquire();
                // Perform action with the connection
                String connectionName = connection.getName();
                LOGGER.info("{} using {}", Thread.currentThread().getName(), connectionName);
                connectionPool.release(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(task);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LOGGER.info("All tasks completed.");
    }

    private static void demonstrateCreateObjectUsingReflection() {
        try {
            // Load the ProductBasicInfo class dynamically
            Class<?> productBasicInfoClass = Class.forName("ProductBasicInfo");

            // Create an instance using the default constructor
            Constructor<?> defaultConstructor = productBasicInfoClass.getConstructor();
            Object productBasicInfo = defaultConstructor.newInstance();

            // Set the name field
            Field nameField = productBasicInfoClass.getDeclaredField("name");
            nameField.setAccessible(true); // Make private field accessible
            nameField.set(productBasicInfo, "Sample Product");

            // Set the category field
            Field categoryField = productBasicInfoClass.getDeclaredField("category");
            categoryField.setAccessible(true);
            categoryField.set(productBasicInfo, Category.ELECTRONICS);

            // Call the getName method
            Method getNameMethod = productBasicInfoClass.getMethod("getName");
            String name = (String) getNameMethod.invoke(productBasicInfo);
            LOGGER.info("Product Name: {}", name);

            // Call the getCategory method
            Method getCategoryMethod = productBasicInfoClass.getMethod("getCategory");
            Category category = (Category) getCategoryMethod.invoke(productBasicInfo);
            LOGGER.info("Product Category: {}", category);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void demonstrateStreamAPI() {
        List<Product> products = Main.getProducts();
        LOGGER.info("Biggest product value: {}", StreamUtils.getBiggestProductValue(products));
        LOGGER.info("First product with price bigger than 150: {}", StreamUtils.findFirstProductWithPriceBiggerThan(products, new BigDecimal("150")));
        LOGGER.info("All products with price bigger than 150: {}", StreamUtils.findAllProductsWithPriceBiggerThan(products, new BigDecimal("150")));
        LOGGER.info("Count of products with price bigger than 150: {}", StreamUtils.countProductsWithPriceBiggerThan(products, new BigDecimal("150")));
        LOGGER.info("Sorted products by price: {}", StreamUtils.sortProductsByPrice(products));
        LOGGER.info("Products prices with VAT: {}", StreamUtils.getProductsPriceWithVatTax(products, new BigDecimal("1.23")));
        LOGGER.info("Any product expensive (>250): {}", StreamUtils.anyExpensive(products, new BigDecimal("250")));
        LOGGER.info("Price with VAT details: {}", StreamUtils.priceWithVatDetails(products, new BigDecimal("1.23")));
        LOGGER.info("Processed products: {}", StreamUtils.processedProducts(products));

        LOGGER.info("All prices: ");
        StreamUtils.showAllPrices(products);

        Optional<String> productName = findProductNameByCategory(products, Category.ELECTRONICS);
    }

    private static void demonstrateReflection() {
        Product product = Main.getProducts().get(0);

        LOGGER.info("Field Names: {}", ReflectionUtils.getFieldNames(product));
        LOGGER.info("Field Modifiers: {}", ReflectionUtils.getModifiers(product));
        LOGGER.info("Field Annotations: {}", ReflectionUtils.getAnnotations(product));
        LOGGER.info("Constructors: {}", ReflectionUtils.getConstructors(product));
    }


    private static List<Product> getProducts() {
        ProductBasicInfo basicInfo = new ProductBasicInfo("X", Category.ELECTRONICS); // Essential information
        PricingInfo pricingInfo = new PricingInfo(BigDecimal.valueOf(10), 10); // Essential information
        Size size = new Size(30f, 20f, 1.5f); // Essential information
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

        return List.of(book, laptop, foodProduct);
    }

    private static void demonstrateCustomLambda() {
        CustomLambda<Integer, String> intToString = number -> "Number: " + number;
        CustomLambda<String, Integer> stringLength = text -> text.length();
        CustomLambda<Integer, Integer> square = num -> num * num;

        CustomLambda<Integer, Integer> lengthOfStringifiedNumber =
                intToString.andThen(stringLength);

        LOGGER.info(intToString.apply(10)); // "Number: 10"
        LOGGER.info(stringLength.apply("Hello World")); // 11
        LOGGER.info(square.apply(5)); // 25
        LOGGER.info(lengthOfStringifiedNumber.apply(10)); // 9
    }

    private static void demonstrateApacheLibraries() {
        try {
            URL inputResource = Main.class.getResource("/bookForKids.txt");
            String inputFilePath = new File(inputResource.toURI()).getAbsolutePath();

            String outputFilePath = "src/main/resources/output.txt";
            int uniqueWordCount = BookService.countWordsInBook(inputFilePath, outputFilePath);

            LOGGER.info("Number of unique words: {}", uniqueWordCount);
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
            LOGGER.error("Error ", e);
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

    public static List<Person> fillPersonArray() {
        EmployeeInfo employeeInfo = new EmployeeInfo(BigDecimal.valueOf(1000), LocalDate.now(), Position.CEO);

        // Setting addressInfo via setters as it is optional for Employee creation
        AddressInfo addressInfo = new AddressInfo();

        try {
            addressInfo.setCountry("Po");
            addressInfo.setCity("Wroclaw");
            addressInfo.setStreet("Opolska");
        } catch (InvalidAddressException e) {
            LOGGER.error("Error ", e);
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
        LOGGER.info("To string methods");
        LOGGER.info(employee); //toString result Person ... and default toString in employeeInfo like info.EmployeeInfo@...
        LOGGER.info(cashier);
        LOGGER.info(person);

//        equalsMethod
        LOGGER.info("Equals methods:");
        LOGGER.info(employee.equals(person)); //true because it compares only by id not by memory
        LOGGER.info(employee.equals(cashier)); //false because id is different

        // hashCode method
        LOGGER.info("hashCode output:");
        LOGGER.info("employee.hashCode(): {}", employee.hashCode());
        LOGGER.info("person.hashCode(): {}", person.hashCode());
        //person and .employee has the same hashcode because equals compares only they id's
        LOGGER.info("cashier.hashCode(): {}", cashier.hashCode());
//        different because of id


//        abstract method getRoleDescription
        LOGGER.info("Abstract method:");
        LOGGER.info(person.getRoleDescription()); //using getRoleDescription from employee because it's instantion of employee
        LOGGER.info(employee.getRoleDescription());
        LOGGER.info(cashier.getRoleDescription());  //using getRoleDescription from employee + adding more responsibilities
        LOGGER.info(person2.getRoleDescription());  //using getRoleDescription from trainee

//        Business method
        LOGGER.info("Business method:");
        EmployeeService.performDuties(person);
        EmployeeService.performDuties(person2);
        EmployeeService.performDuties(employee);
        EmployeeService.performDuties(cashier);


//       field of superclass
        LOGGER.info("Super class field");
        cashier.setSupervisor(person);
        LOGGER.info("Supervisor of manager (generalEmployee): {}", cashier.getSupervisorDescription()); //get responsibilities from employee

        cashier.setSupervisor(person2);
        LOGGER.info("Supervisor of manager (seniorManager): {}", cashier.getSupervisorDescription()); //get responsibilities from trainee

        LOGGER.info("Protected keyword");
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
        LOGGER.info("Product calculator ");
        LOGGER.info("Calculate Profit {}", ProductCalculator.calculateProfit(
                laptop.getPricingInfo().getPrice(),
                Laptop.getProductionCost()));
        LOGGER.info("Get available discount {}", ProductCalculator.getAvailableDiscount());

        LOGGER.info("Final method");
        LOGGER.info("Calculate Surface Area {}", laptop.calculateSurfaceArea());

//        Interfaces
        LOGGER.info("ISBN number {}", book.isValidISBN());
        LOGGER.info("ISBN number {}", book.getISBN());

        LOGGER.info("Interface check method laptop lightweight{}", laptop.isLightweight());

        LOGGER.info("Inteface as field in class");
        Warehouse warehouse = new Warehouse(foodProduct);
        warehouse.checkProductStatus();

        LOGGER.info("Inteface as parameter in method ");
        checkSpoilage(foodProduct);

        LOGGER.info("Collection ");
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
            LOGGER.warn("Error while setting salary: ", e);
        }


        Size size = new Size(10f, 10f, 10f);
        try {
            //Unchecked  Assuming user provides height of a product after some accident
            // don't need to be surrounded by try catch block
            size.setHeight(-10f);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Error while setting height: ", e);
        }

        try {
            // Checked exception assuming the ID number is auto-created by a database
            // don't need to be surrounded by try catch block
            Cashier cashier = new Cashier(-123, "X", "XX", employeeInfo, 647);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error while creating cashier: ", e);
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

        LOGGER.info("EmployeeService Set");
        LOGGER.info("Savings {}", EmployeeService.decreasePaidForEmployeesAndCalculateSavings(
                Set.of(employee, cashier), 5));

        LOGGER.info("Product Service List");
        LOGGER.info("Needed space {}", ProductService.countTotalSpaceForProductsInWareHouse(Main.setProductList()));

        LOGGER.info("HashMap");
        LOGGER.info("Group employees");
        Map<String, List<Employee>> mapOfGroupedEmployees = EmployeeService.groupEmployeesByPosition(List.of(employee, employee2, cashier));

        for (String position : mapOfGroupedEmployees.keySet()) {
            LOGGER.info("Position {} {}", position, mapOfGroupedEmployees.get(position));
        }

        LOGGER.info("Most paid employee {}", EmployeeService.findMostPaidEmployee(Set.of(employee, employee2, cashier)));

        LOGGER.info("Product Categories by Size:");
        Map<String, List<Product>> productCategories = ProductService.categorizeProductsBySize(Main.setProductList());
        for (Map.Entry<String, List<Product>> entry : productCategories.entrySet()) {
            LOGGER.info("Category: {} - Products: {}", entry.getKey(), entry.getValue());
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


        LOGGER.info("First employee {}", customLinkedList.get(0));

//Add element to specified index
        customLinkedList.add(0, cashier);
        LOGGER.info("First employee {}", customLinkedList.get(0));

//        Remove element
        customLinkedList.remove(cashier);
        LOGGER.info("First employee after remove {}", customLinkedList.get(0));
        customLinkedList.add(0, cashier);


        LOGGER.info("Does list contains 'Cashier'? {}", customLinkedList.contains(cashier));

        // Get index element
        LOGGER.info("'Cashier' index : {}", customLinkedList.indexOf(cashier));

//        foreach loop
        for (Employee employee3 : customLinkedList) {
            LOGGER.info("Employee in loop {}", employee3.getName());
        }

        customLinkedList.clear();
        LOGGER.info("After clear : {}", customLinkedList.indexOf(cashier));

    }

    public static void demonstrateEnums() {
        ProductBasicInfo basicInfo = new ProductBasicInfo("X", Category.ELECTRONICS); // Essential information
        PricingInfo pricingInfo = new PricingInfo(BigDecimal.valueOf(10), 10); // Essential information
        Size size = new Size(30f, 20f, 1.5f); // Essential information
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


        book.setPriorityType(PriorityType.HIGH);
        LOGGER.info("Message to supplier{} ", book.getPriorityType().messageToSupplier());

        switch (book.getPriorityType()) {
            case LOW -> LOGGER.info("Low priority 5 days to go..");
            case MEDIUM -> LOGGER.info("Medium priority 2 days to go ...");
            case HIGH -> LOGGER.warn("High priority send it immediately");
            default -> {
                LOGGER.error("Unknown priority type");
                throw new InvalidArgumentException("Unknown priority type");
            }
        }

        LOGGER.info("Planet delivery info " + Planets.EARTH.deliverPlanetTime(book.getBasicInfo().getName()));

        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;

        switch (paymentMethod) {
            case CASH -> LOGGER.info("You selected CASH. Please have the exact amount ready.");
            case CREDIT_CARD -> LOGGER.info("You selected CREDIT CARD. Processing your payment...");
            case DEBIT_CARD -> LOGGER.info("You selected DEBIT CARD. Processing your payment...");
            case PAYPAL -> LOGGER.info("You selected PAYPAL. Redirecting to PayPal...");
            default -> LOGGER.info("Unknown payment method.");
        }

        DayOfWeek today = DayOfWeek.SATURDAY;

        if (isWeekend(today)) {
            LOGGER.info("It's the weekend! Time to relax.");
        } else {
            LOGGER.info("It's a weekday. Back to work!");
        }
    }

    public static void demonstrateLambdas() {
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

        List<Employee> employeeList = List.of(employee, employee2, cashier);

//        Consumer,Supplier interface
        EmployeeService.changeSalary(employeeList, GeneralUtils.generateRandomValue(employeeList).get());

//        Function interface
        LOGGER.info("Biggest salary: {}", EmployeeService.getBiggestSalary().apply(employeeList));
        LOGGER.info("Get field from class that we want: {}", GeneralUtils.transformList(employeeList, employeee -> employeee.getEmployeeInfo().getSalary()));

//        Predicate interface
        LOGGER.info("Custom filter: {}",
                GeneralUtils.customFilter(employeeList, employee1 -> employee1.getEmployeeInfo().getSalary().compareTo(BigDecimal.valueOf(5000)) > 0));


//        Runnable interface
        Runnable uploadTask = () -> AdvancedFileUploader.uploadFile("user_photo.png");

        Thread uploadThread = new Thread(uploadTask);
        uploadThread.start();

        LOGGER.info("User can do other actions");
        AdvancedFileUploader.performOtherActions();


        // Using processEmployees to calculate yearly bonuses
        EmployeeService.processEmployees(employeeList, emp -> {
            BigDecimal bonus = emp.getEmployeeInfo().getSalary().multiply(BigDecimal.valueOf(0.1));
            return "Yearly Bonus: " + bonus;
        });

        // Logging employee names
        employeeList.forEach(emp -> {
            String name = emp.getName();
            LOGGER.info("Employee Name: {}", name);
        });

    }

    private static void handleAddressSetting(AddressInfo addressInfo) {
        try {
            addressInfo.setStreet("Street1");
            addressInfo.setCountry("P"); // This will throw an exception
            addressInfo.setCity("Wroclaw");
        } catch (InvalidAddressException e) {
            LOGGER.error("Error while setting address in helper method", e);
        }
    }


    public static void getFileInfo() {
        String filePath = "example.txt";
        try {
            List<String> fileContents = FileReadWithResourcesUtil.getResourcesFromFile(filePath);
            fileContents.forEach(System.out::println);
        } catch (FileProcessingException e) {
            LOGGER.error("An error occurred:", e);
        }

        try {
            List<String> fileContents = FileReadUtil.getResourcesFromFile(filePath);
            fileContents.forEach(System.out::println);
        } catch (FileProcessingException e) {
            LOGGER.error("An error occurred: ", e);
        }

    }

    public static void checkSpoilage(Spoiled item) {
        if (item.isSpoiled()) {
            LOGGER.warn("The product is spoiled");
        } else {
            LOGGER.info("The product is still good.");
        }
    }

    public static void checkAllSpoiled(List<Spoiled> items) {
        for (Spoiled item : items) {
            LOGGER.info("Checking product...");
            if (item.isSpoiled()) {
                LOGGER.warn("The product is spoiled.");
            } else {
                LOGGER.info("The product is in good condition.");
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

    public static boolean isWeekend(DayOfWeek day) {
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

}
