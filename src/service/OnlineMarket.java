package service;

import employee.Employee;
import product.Product;

public class OnlineMarket {

    private Product[] products;
    private Employee[] employees;

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
}

