package es.ulpgc.hpi.p3.projectmanager;

public class Company {

    private static int ID = 0;
    private final int id;
    private final String name;
    private final String address;

    public Company(String name, String address) {
        this.id = ++ID;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
