package es.ulpgc.hpi.p3.projectmanager;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private static int ID = 0;
    private final int id;
    private final String name;
    private final List<Product> productList = new ArrayList<>();

    public Project(String name) {
        this.id = ++ID;
        this.name = name;
    }

    private void addProduct(Product product){
        this.productList.add(product);
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }
}
