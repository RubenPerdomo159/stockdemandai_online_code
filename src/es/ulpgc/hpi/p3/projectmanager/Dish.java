package es.ulpgc.hpi.p3.projectmanager;

public class Dish {

    private static int ID = 0;
    private final int id;
    private final String name;

    public Dish(String name) {
        this.id = ++ID;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + "id:" + id + " - " + name + "}";
    }
}
