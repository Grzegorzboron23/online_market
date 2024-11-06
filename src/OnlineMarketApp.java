import attribute.Size;
import employee.CEO;
import enumeration.Category;
import enumeration.Position;
import info.*;
import product.Book;
import product.Product;
import service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OnlineMarketApp {
    public static void main(String[] args) {


        AddressInfo ceoAddress = new AddressInfo("X", "XX", "XXX");
        EmployeeInfo ceoInfo = new EmployeeInfo(BigDecimal.valueOf(150000.0), LocalDate.of(2015, 5, 20), Position.CEO);
        CEO ceo = new CEO(ceoInfo, ceoAddress);

        System.out.println("CEO Details:");
        System.out.println("Position: " + ceo.getEmployeesInfo().getPosition());
        System.out.println("Salary: " + ceo.getEmployeesInfo().getSalary());
        System.out.println("Address: " + ceoAddress.getCity() + ", " + ceoAddress.getStreet());

        List<Product> products = new ArrayList<>();

        Book book1 = new Book.Builder(
                new ProductBasicInfo("Y", Category.BOOKS),
                new PricingInfo(BigDecimal.valueOf(45.99), 1),
                new Size(15f, 22f, 3f),
                new ProductDetailsInfo("YY", true)
        )
                .author("YYY")
                .publisher("YYYY")
                .pageCount(412)
                .genre("YYYYY")
                .build();

        Book book2 = new Book.Builder(
                new ProductBasicInfo("Y", Category.BOOKS),
                new PricingInfo(BigDecimal.valueOf(45.99), 2),
                new Size(15f, 22f, 3f),
                new ProductDetailsInfo("YY", true)
        )
                .author("YYY")
                .publisher("YYYY")
                .pageCount(412)
                .genre("YYYYY")
                .build();

        products.add(book1);
        products.add(book2);

        System.out.println("Products details");
        for (Product product : products) {
            Book book = (Book) product;
            System.out.println("Title: " + book.getBasicInfo().getName());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Price: " + book.getPricingInfo().getPrice());
            System.out.println("----");
        }

        System.out.println("Total value " + ProductService.countTotalValue(products));
    }
}
