package es.ulpgc.hpi.p3.projectmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Company> companyList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = askCommand(scanner);
        while (!command.equals("exit")) {
            processCommand(command);
            command = askCommand(scanner);
        }
    }

    private static void processCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "add-company" -> {
                if (Validation.validate(parts, 3, -1)) {
                    addCompany(parts);
                }
            }
            case "get-company" -> {
                if (Validation.validate(parts, 2, -1)) {
                    getCompany(parts[1]);
                }
            }
            case "add-dish" -> {
                if (Validation.validate(parts, 3, -1)) {
                    addDish(parts);
                }
            }
            case "add-ingredient" -> {
                if (Validation.validate(parts, 4, 2)) {
                    addIngredient(parts);
                }
            }
            case "add-dish-ingredient" -> {
                if (Validation.validate(parts, 4, -1)) {
                    addDishIngredient(parts);
                }
            }
            case "get-menu" -> {
                if (Validation.validate(parts, 2, -1)) {
                    getMenu(parts);
                }
            }
            case "get-company-ingredients" -> {
                if (Validation.validate(parts, 2, -1)) {
                    getCompanyIngredients(parts);
                }
            }

            case "get-stock" -> {
                if (Validation.validate(parts, 3, -1)) {
                    getStock(parts);
                }
            }
            case "get-dish-ingredients" -> {
                if (Validation.validate(parts, 3, -1)) {
                    getDishIngredients(parts);
                }
            }

            case "update-stock" -> {
                if (Validation.validate(parts, 4, 2)) {
                    updateStock(parts);
                }
            }

            case "get-company-stock" -> {
                if (Validation.validate(parts, 2, -1)) {
                    getCompanyStock(parts);
                }
            }

            case "make-prediction" -> {
                if (Validation.validate(parts, 3, -1)) {
                    makePrediction(parts);
                }
            }

            case "help" -> {
                if (Validation.validate(parts, 1, -1)) {
                    help();
                }
            }
            default -> System.out.println("Command not found");
        }

    }

    private static void addDishIngredient(String[] parts) {
        Company company = findCompany(parts[3]);
        if (company == null) {
            System.out.println("Company not found");
            return;
        }

        Dish dish = findDish(parts[2], company);
        if (dish == null) {
            System.out.println("Dish not found in the company");
            return;
        }

        Ingredients ingredient = findIngredient(parts[1], company);
        if (ingredient == null) {
            System.out.println("Ingredient not found in company");
            return;
        }

        dish.addIngredient(ingredient);
        System.out.println("Ingredient added to dish successfully");
    }

    private static void addCompany(String[] parts) {
        companyList.add(new Company(parts[1], parts[2]));
    }

    private static void getCompany(String companyName) {
        for (Company c : companyList) {
            if (c.getName().equals(companyName)) {
                System.out.println("Company " + companyName + " with id: " + c.getId() + ", have address " + c.getAddress());
                return;
            }
        }
        System.out.println("Unregistered company");
    }

    private static Company findCompany(String companyName) {
        for (Company c : companyList) {
            if (c.getName().equals(companyName)) {
                return c;
            }
        }
        return null;
    }

    private static Dish findDish(String DishName, Company CompanyName) {
        for (Dish d : CompanyName.getDishList()) {
            if (d.getName().equals(DishName)){
                return d;
            }
        }
        return null;
    }

    private static void addDish(String[] parts) {
        Company company = findCompany(parts[2]);
        if (company != null) {
            company.addDish((new Dish(parts[1])));
            System.out.println("Dish successfully added to " + company.getName());
            return;
        }
        System.out.println("Company not found");
    }

    private static Ingredients findIngredient(String ingredients, Company companyName) {
        for (Ingredients i : companyName.getIngredientsList()) {
            if (i.getName().equals(ingredients)){
                return i;
            }
        }
        return null;
    }

    private static void getMenu(String[] parts) {
        Company company = findCompany(parts[1]);
        if (company != null) {
            List<String> dishNames = company.getDishesName();
            if (!dishNames.isEmpty()) {
                System.out.print(company.getName() + " have: ");
                System.out.println(String.join(", ", dishNames));
                return;
            }
            System.out.println("There is no menu");
            return;
        }
        System.out.println("Company not found");
    }

    private static void addIngredient(String[] parts) {
        Company company = findCompany(parts[3]);
        if (company != null) {
            company.addIngredient((new Ingredients(parts[1], Integer.parseInt(parts[2]))));
            System.out.println("Ingredient successfully added to " + company.getName());
            return;
        }
        System.out.println("Company not found");
    }

    private static void getStock(String[] parts) {

        Company company = findCompany(parts[2]);
        if (company != null){
            Ingredients ingredient = findIngredient(parts[1], company);
            if (ingredient != null){
                System.out.println(ingredient);
                return;
            }
            System.out.println("That ingredient is not in the menu");
            return;
        }
        System.out.println("Company not found");
    }

    private static void getDishIngredients(String[] parts) {
        Company company = findCompany(parts[2]);
        if (company != null) {
            Dish dish = findDish(parts[1], company);
            if (dish != null) {
                List<String> ingredientNames = dish.getIngredientsName();
                if (!ingredientNames.isEmpty()) {
                    System.out.print("Ingredients of " + dish.getName() + ": ");
                    System.out.println(String.join(", ", ingredientNames));
                    return;
                }
                System.out.println("The dish has no ingredients");
                return;
            }
            System.out.println("Dish not found in the menu");
            return;
        }
        System.out.println("Company not found");
    }

    private static void getCompanyIngredients(String[] parts) {
        Company company = findCompany(parts[1]);
        if (company != null) {
            List<String> ingredientNames = company.getIngredientsName();
            if (!ingredientNames.isEmpty()) {
                System.out.print(company.getName() + " has: ");
                System.out.println(String.join(", ", ingredientNames));
                return;
            }
            System.out.println("There are no ingredients");
            return;
        }
        System.out.println("Company not found");
    }

    private static void updateStock(String[] parts) {
        Company company = findCompany(parts[3]);
        if (company == null) {
            System.out.println("Company not found");
            return;
        }

        Ingredients ingredient = findIngredient(parts[1], company);
        if (ingredient == null) {
            System.out.println("Ingredient not found");
            return;
        }

        int newStock = Integer.parseInt(parts[2]);
        ingredient.setStock(newStock);

        System.out.println("Stock updated: " + ingredient.getName() + " now has " + newStock);
    }

    private static void getCompanyStock(String[] parts) {
        Company company = findCompany(parts[1]);
        if (company == null) {
            System.out.println("Company not found");
            return;
        }

        List<Ingredients> ingredients = company.getIngredientsList();
        if (ingredients.isEmpty()) {
            System.out.println("There are no ingredients");
            return;
        }

        System.out.println("Stock of " + company.getName() + ":");
        for (Ingredients i : ingredients) {
            System.out.println(" - " + i.getName() + ": " + i.getStock());
        }
    }

    private static void makePrediction(String[] parts) {
        Company company = findCompany(parts[2]);
        if (company == null) {
            System.out.println("Company not found");
            return;
        }

        Dish dish = findDish(parts[1], company);
        if (dish == null) {
            System.out.println("Dish not found");
            return;
        }

        List<Ingredients> ingredients = dish.getIngredients();
        if (ingredients.isEmpty()) {
            System.out.println("The dish has no ingredients defined");
            return;
        }

        int maxPlates = Integer.MAX_VALUE;

        for (Ingredients ingredient : ingredients) {
            if (ingredient.getStock() < maxPlates) {
                maxPlates = ingredient.getStock();
            }
        }

        System.out.println("You can prepare " + maxPlates + " dishes");
    }

    private static String askCommand(Scanner scanner) {
        System.out.println("Write a command\n" + "(Write \"help\" if you don't know any command)");
        return scanner.nextLine().trim();
    }

    private static void help() {
        System.out.println("""
                exit: Exit the program (command)
                add-company: Add a company to the list (command name address)
                get-company: Return a company with its name (command name)
                add-dish: Add a dish to a company (command name companyName)
                add-ingredient: Add a ingredient to a company (command name stock companyName)
                add-dish-ingredient: Add a ingredient to a dish (command ingredientName dishName companyName)
                get-company-ingredients: Return the ingredients of a company (command companyName)
                get-dish-ingredients: Return the ingredients of a dish (command dishName companyName)
                get-menu: Return the menu of a company (command companyName)
                get-stock: Return the stock of a ingredient (command ingredientName companyName)
                update-stock: Update the stock of an ingredient (command ingredientName newStock companyName)
                get-company-stock: Return the stock of a company (command companyName)
                make-prediction: Make a prediction of the number of dishes you can make (command dishName companyName)
                """);
    }
}
