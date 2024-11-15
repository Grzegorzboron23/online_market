import attribute.Size;
import employee.*;
import enumeration.Category;
import enumeration.Position;
import info.*;
import product.Book;
import product.FoodProduct;
import product.Laptop;
import product.Product;
import productInterface.Spoiled;
import profitCalculator.ProductCalculator;
import service.EmployeeService;
import service.OnlineMarket;
import warehouse.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Product[] productArray = Main.fillProductArray();
        Employee[] employeeArray = Main.fillEmployeeArray();

        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setProducts(productArray);  // Setter used as product list may be modified later
        onlineMarket.setEmployees(employeeArray); // Setter used as employee list may be modified later

//        System.out.println("Products: " + onlineMarket.getProducts());
//        System.out.println("Employees: " + onlineMarket.getEmployees());

//        System.out.println("Value of products: " + ProductService.countTotalValue(onlineMarket.getProducts()));

//        Main.demonstrateObjectMethodOverrides();
        Main.demonstrateFinalStaticAndInterface();
    }

    public static Employee[] fillEmployeeArray() {
        EmployeeInfo employeeInfo = new EmployeeInfo(BigDecimal.valueOf(1000), LocalDate.now(), Position.CEO);

        // Setting addressInfo via setters as it is optional for Employee creation
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setCountry("Poland");
        addressInfo.setCity("Wroclaw");
        addressInfo.setStreet("Opolska");

        CEO ceo = new CEO(123456789, "X", "XX", employeeInfo); // employeeInfo is essential for CEO creation, so it's in the constructor
        ceo.setAddressInfo(addressInfo); // addressInfo is optional, so it's set through a setter

        Manager manager = new Manager(123456789, "X", "XX", employeeInfo); // employeeInfo is essential, so it's in the constructor
        manager.setAddressInfo(addressInfo); // Optional addressInfo is set through a setter
        manager.setTeamSize(10); // teamSize can vary, so it's set via a setter

        Cashier cashier = new Cashier(123456789, "X", "XX", employeeInfo, 16); // employeeInfo is essential, passed through the constructor
        cashier.setAddressInfo(addressInfo); // Optional addressInfo is set through a setter

        return new Employee[]{ceo, manager, cashier};
    }

    public static Product[] fillProductArray() {
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

        return new Product[]{laptop, book, foodProduct};
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
}
