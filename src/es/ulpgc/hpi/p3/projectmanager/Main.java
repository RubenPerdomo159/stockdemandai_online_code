package es.ulpgc.hpi.p3.projectmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final List<Company> companyList = new ArrayList<>();
    private static final List<Product> productList = new ArrayList<>();

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
        if (parts[0].equals("add-company")) addCompany(parts);
        else if (parts[0].equals("add-product")) addProduct(parts);
        else if (parts[0].equals("find-company")) findCompany(parts[1]);

    }

    private static void addCompany(String[] parts) {
        companyList.add(new Company(parts[1], parts[2]));
    }

    private static void addProduct(String[] parts) {
        productList.add(new Product(parts[1], Integer.parseInt(parts[2]), findCompany(parts[3])));
    }

    private static Company findCompany(String studentId) {
        int id = Integer.parseInt(studentId);
        for (Company c : companyList) {
            if (c.getId() == id) {
                System.out.println("Company " + id + " is " + c.getName() + " with address " + c.getAddress());
                return c;
            }
        }
        System.out.println("Unregistered company");
        return null;
    }

    private static String askCommand(Scanner scanner) {
        System.out.println("Write a command\n" + help());
        return scanner.nextLine().trim();
    }

    private static String help() {
        return "exit";
    }

}
