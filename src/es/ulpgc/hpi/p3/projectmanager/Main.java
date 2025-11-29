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
            case "add-company" -> addCompany(parts);
            case "get-company" -> getCompany(parts[1]);
            case "add-dish" -> addDish(parts);
            case "get-menu" -> getMenu(parts);
            case "get-stock" -> getStock(parts);
            case "get-company-stock" -> getCompanyStock(parts);
            case "help" -> help();
        }

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
        Company company = findCompany(parts[3]);
        if (company != null) {
            company.addDish((new Dish(parts[1], Integer.parseInt(parts[2]))));
            System.out.println("Dish successfully added to " + company.getName());
            return;
        }
        System.out.println("Company not found");
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

    private static void getStock(String[] parts) {

        Company company = findCompany(parts[1]);
        if (company != null){
            Dish dish = findDish(parts[2], company);
            if (dish != null){
                System.out.println(dish);
                return;
            }
            System.out.println("That dish is not in the menu");
            return;
        }
        System.out.println("Company not found");
    }

    private static void getCompanyStock(String[] parts) {
        Company company = findCompany(parts[1]);
        if (company != null){
            if (company.getDishList() != null) {
                for(Dish d : company.getDishList()){
                    System.out.println(d);
                }
                return;
            }
            System.out.println("There is no menu");
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
                get-company: Find a company with its name (command name)
                add-dish: Add a dish to a company (command name stock companyName)
                get-menu: Find the menu of a company (command companyName)
                get-stock: Returns the stock of a dish (command companyName dishName)
                get-company-stock: Returns the stock of a company (command companyName)
                """);
    }

}