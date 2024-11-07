import attribute.Size;
import employee.CEO;
import employee.Cashier;
import employee.Employee;
import employee.Manager;
import enumeration.Category;
import info.*;
import product.Book;
import product.FoodProduct;
import product.Laptop;
import product.Product;
import service.OnlineMarket;
import service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Product> productList = Main.fillProductList();
        List<Employee> employeeList = Main.fillEmployeeList();

        OnlineMarket onlineMarket = new OnlineMarket();
        onlineMarket.setProducts(productList);
        onlineMarket.setEmployees(employeeList);

        System.out.println("Products: " + onlineMarket.getProducts());
        System.out.println("Employees: " + onlineMarket.getEmployees());

        System.out.println("Value of products " + ProductService.countTotalValue(onlineMarket.getProducts()));
    }

    public static List<Employee> fillEmployeeList(){
        EmployeeInfo employeeInfo = new EmployeeInfo();
        AddressInfo addressInfo = new AddressInfo();

        CEO ceo = new CEO(employeeInfo,addressInfo);
        Manager manager = new Manager(employeeInfo,addressInfo,10);
        Cashier cashier = new Cashier(employeeInfo,addressInfo,1);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(ceo);
        employeeList.add(manager);
        employeeList.add(cashier);

        return employeeList;
    }

    public static List<Product> fillProductList(){
        ProductBasicInfo basicInfo = new ProductBasicInfo("X" , Category.ELECTRONICS);
        PricingInfo pricingInfo = new PricingInfo(BigDecimal.valueOf(10), 10);
        Size size = new Size(30f, 20f, 1.5f);
        ProductDetailsInfo productDetailsInfo = new ProductDetailsInfo("X",true);

        Laptop laptop = new Laptop.Builder(basicInfo, pricingInfo, size, productDetailsInfo)
                .brand("X")
                .processor("XX")
                .operatingSystem("XXX")
                .build();

        Book book = new Book.Builder(basicInfo, pricingInfo, size, productDetailsInfo)
                .author("X")
                .genre("XX")
                .pageCount(1)
                .publisher("XXXX")
                .build();

        FoodProduct foodProduct = new FoodProduct.Builder(basicInfo, pricingInfo, size, productDetailsInfo)
                .brand("X")
                .expirationDate(LocalDateTime.now())
                .isOrganic(true)
                .build();


        List<Product> products = new ArrayList<>();
        products.add(laptop);
        products.add(book);
        products.add(foodProduct);

        return products;
    }
}

