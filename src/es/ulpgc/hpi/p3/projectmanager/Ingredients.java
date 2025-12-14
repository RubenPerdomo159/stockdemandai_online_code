package es.ulpgc.hpi.p3.projectmanager;

public class Ingredients {
    private final String name;
    private int stock;

    public Ingredients (String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {return this.name;}

    public int getStock() {return stock;}

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {return  name + ": " + stock;}
}
