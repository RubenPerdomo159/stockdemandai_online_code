package es.ulpgc.hpi.p3.projectmanager;

public class Dish {

    private static int ID = 0;
    private final int id;
    private final String name;
    private int stock;

    public Dish(String name, int stock) {
        this.id = ++ID;
        this.name = name;
        this.stock = stock;
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

    public String getStockText() {
        return name + Integer.toString(stock);
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "{" + "id:" + id + " - " + name + ", stock=" + stock + "}";
    }
}
