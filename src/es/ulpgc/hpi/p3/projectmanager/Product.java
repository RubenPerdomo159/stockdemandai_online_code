package es.ulpgc.hpi.p3.projectmanager;

import java.time.LocalDateTime;

public class Product {

    private static int ID = 0;
    private final int id;
    private final String name;
    private int stock;
    private Company responsible;

    public Product(String name, int stock, Company responsible) {
        this.id = ++ID;
        this.name = name;
        this.stock = stock;
        this.responsible = responsible;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public Company getResponsible() {
        return responsible;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setResponsible(Company responsible) {
        this.responsible = responsible;
    }
}
