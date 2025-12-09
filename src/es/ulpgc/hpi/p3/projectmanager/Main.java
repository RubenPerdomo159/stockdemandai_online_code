package es.ulpgc.hpi.p3.projectmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                if (validate(parts, 3, -1)) {
                    addCompany(parts);
                }
            }
            case "get-company" -> {
                if (validate(parts, 1, -1)) {
                    getCompany(parts[1]);
                }
            }
            case "add-dish" -> {
                if (validate(parts, 3, -1)) {
                    addDish(parts);
                }
            }
            case "add-ingredient" -> {
                if (validate(parts, 4, 2)) {
                    addIngredient(parts);
                }
            }
            case "get-menu" -> {
                if (validate(parts, 2, -1)) {
                    getMenu(parts);
                }
            }
            case "get-stock" -> {
                if (validate(parts, 3, -1)) {
                    getStock(parts);
                }
            }
            case "help" -> {
                if (validate(parts, 1, -1)) {
                    help();
                }
            }
            default -> System.out.println("Command not found");
        }

    }

    public static boolean validate(String[] parts, int expectedLength, int positionToCheck) {
        if (parts.length != expectedLength) {
            System.out.println("Incorrect command length");
            return false;
        } else if (positionToCheck >= 0 && !parts[positionToCheck].matches("\\d+")) {
            System.out.println("Incorrect attribute type");
            return false;
        }

        return true;
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
                *get-ingredients: Return the ingredients of a company (command companyName)
                get-menu: Return the menu of a company (command companyName)
                get-stock: Return the stock of a ingredient (command ingredientName companyName)
                *get-company-stock: Return the stock of a company (command companyName)
                *make-prediction: Make a prediction of the number of dishes you can make (command dishName companyName)
                """);
    }

}