package es.ulpgc.hpi.p3.projectmanager;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private static int ID = 0;
    private final int id;
    private final String name;
    private final String address;
    private final List<Dish> dishList = new ArrayList<>();
    private final List<Ingredients> ingredientList = new ArrayList<>();

    public Company(String name, String address) {
        this.id = ++ID;
        this.name = name;
        this.address = address;
    }

    public void addDish(Dish dish){
        this.dishList.add(dish);
    }

    public List<Dish> getDishList() {
        return new ArrayList<>(dishList);
    }

    public List<String> getDishesName() {
        List<String> dishes = new ArrayList<>();
        for (Dish d : dishList) {
            dishes.add(d.getName());
        }
        return dishes;
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

    public void addIngredient(Ingredients ingredient){
        this.ingredientList.add(ingredient);
    }

    public List<Ingredients> getIngredientsList() {
        return new ArrayList<>(ingredientList);
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name=" + name + ", address=" + address + '}';
    }
}
