package es.ulpgc.hpi.p3.projectmanager;

import java.util.ArrayList;
import java.util.List;

public class Dish {

    private static int ID = 0;
    private final int id;
    private final String name;
    private final List<Ingredients> ingredients = new ArrayList<>();

    public Dish(String name) {
        this.id = ++ID;
        this.name = name;
    }

    public void addIngredient(Ingredients ingredient) {
        ingredients.add(ingredient);
    }

    public List<String> getIngredientsName() {
        List<String> ingredientsName = new ArrayList<>();
        for (Ingredients i : ingredients) {
            ingredientsName.add(i.getName());
        }
        return ingredientsName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + "id:" + id + " - " + name + "}";
    }
}
