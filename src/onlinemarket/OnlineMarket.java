package onlinemarket;


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

public class OnlineMarket {
    private CEO ceo;
    private List<Product> products;

    public OnlineMarket() {
        AddressInfo ceoAddress = new AddressInfo("X", "XX", "XXX");
        EmployeeInfo ceoInfo = new EmployeeInfo(BigDecimal.valueOf(150000.0), LocalDate.of(2015, 5, 20), Position.CEO);
        this.ceo = new CEO(ceoInfo, ceoAddress);

        this.products = new ArrayList<>();

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
    }

    public CEO getCeo() {
        return ceo;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal calculateTotalValue() {
        return ProductService.countTotalValue(products);
    }
}
