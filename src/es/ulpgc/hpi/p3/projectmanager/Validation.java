package es.ulpgc.hpi.p3.projectmanager;

public class Validation {
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
}
