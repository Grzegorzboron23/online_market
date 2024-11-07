package service;

import employee.Employee;
import product.Product;

import java.util.List;

public class OnlineMarket {

    private List<Product> products;
    private List<Employee> employees;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

