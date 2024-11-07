import attribute.Size;
import employee.CEO;
import employee.Cashier;
import employee.Employee;
import employee.Manager;
import enumeration.Category;
import enumeration.Position;
import info.*;
import product.Book;
import product.FoodProduct;
import product.Laptop;
import product.Product;
import service.OnlineMarket;
import service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Product> productList = Main.fillProductList();
        List<Employee> employeeList = Main.fillEmployeeList();

        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setProducts(productList);  // Setter used as product list may be modified later
        onlineMarket.setEmployees(employeeList); // Setter used as employee list may be modified later

        System.out.println("Products: " + onlineMarket.getProducts());
        System.out.println("Employees: " + onlineMarket.getEmployees());

        System.out.println("Value of products: " + ProductService.countTotalValue(onlineMarket.getProducts()));
    }

    public static List<Employee> fillEmployeeList() {
        EmployeeInfo employeeInfo = new EmployeeInfo(BigDecimal.valueOf(1000), LocalDate.now(), Position.CEO);

        // Setting addressInfo via setters as it is optional for Employee creation
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setCountry("Poland");
        addressInfo.setCity("Wroclaw");
        addressInfo.setStreet("Opolska");

        CEO ceo = new CEO(employeeInfo); // employeeInfo is essential for CEO creation, so it's in the constructor
        ceo.setAddressInfo(addressInfo); // addressInfo is optional, so it's set through a setter

        Manager manager = new Manager(employeeInfo); // employeeInfo is essential, so it's in the constructor
        manager.setAddressInfo(addressInfo); // Optional addressInfo is set through a setter
        manager.setTeamSize(10); // teamSize can vary, so it's set via a setter

        Cashier cashier = new Cashier(employeeInfo, 1); // employeeInfo is essential, passed through the constructor
        cashier.setAddressInfo(addressInfo); // Optional addressInfo is set through a setter

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(ceo);
        employeeList.add(manager);
        employeeList.add(cashier);

        return employeeList;
    }

    public static List<Product> fillProductList() {
        ProductBasicInfo basicInfo = new ProductBasicInfo("X", Category.ELECTRONICS); // Essential information
        PricingInfo pricingInfo = new PricingInfo(BigDecimal.valueOf(10), 10); // Essential information
        Size size = new Size(30f, 20f, 1.5f); // Essential information

        // productDetailsInfo set via setters as it is optional information
        ProductDetailsInfo productDetailsInfo = new ProductDetailsInfo();
        productDetailsInfo.setDescription("X");
        productDetailsInfo.setIsAvailable(true);

        Book book = new Book(basicInfo, pricingInfo, size,
                "Author Name", 200, "Fiction"); // Essential book details passed via constructor
        book.setProductDetails(productDetailsInfo); // Optional product details are set via setter
        book.setPublisher("publisher name"); // Publisher may be assigned later, so it's set via setter

        Laptop laptop = new Laptop(basicInfo, pricingInfo, size,
                "BrandName", "Intel i7", "Windows 10"); // Essential laptop details passed via constructor
        laptop.setProductDetails(productDetailsInfo); // Optional product details set via setter

        FoodProduct foodProduct = new FoodProduct(basicInfo, pricingInfo, size,
                "OrganicBrand"); // Brand is essential, so passed via constructor
        foodProduct.setProductDetails(productDetailsInfo); // Optional product details set via setter
        foodProduct.setIsOrganic(true); // isOrganic is optional, set via setter

        List<Product> products = new ArrayList<>();
        products.add(laptop);
        products.add(book);
        products.add(foodProduct);

        return products;
    }
}
