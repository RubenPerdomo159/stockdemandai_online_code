package es.ulpgc.hpi.p3.projectmanager;

public class Ingredients {
    private final String name;
    private int supplies;

    public Ingredients (String name, int supplies) {
        this.name = name;
        this.supplies = supplies;
    }

    public String getName() {return this.name;}

    public int getSupplies() {return supplies;}

    public void setSupplies(int supplies) {
        this.supplies = supplies;
    }

    @Override
    public String toString() {return  "{name: " + name + ", supplies: " + supplies + "}";}
}
