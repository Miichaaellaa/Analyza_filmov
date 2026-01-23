package sk.spse.movie;
import java.util.Scanner;

public class InputValidator {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Vstup nemôže byť prázdny :(");
                continue;
            }

            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.printf("Zadajte prosím číslo medzi %d a %d\n", min, max);
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Neplatný formát. Skúste zadať celé čísla od 1 do 4.");
            }
        }
    }

    public static String getString(String prompt, int maxLength) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Vstup nemôže byť prázdny :(");
                continue;
            }

            if (input.length() > maxLength) {
                System.out.printf("Vstup je príliš dlhý, prosím zadajte vstup o maxime %d znakov\n", maxLength);
                continue;
            }

            if (containsIllegalChars(input)) {
                System.out.println("Vstup obsahuje nepovolené znaky.");
                continue;
            }

            return input;
        }
    }

    public static int getYear(String prompt) {
        return getInt(prompt, 1888, 2024);
    }

    public static int getMenuChoice(String prompt, int options) {
        return getInt(prompt, 1, options);
    }

    public static void waitForEnter() {
        System.out.print("Stlačte Enter pre pokračovanie...");
        scanner.nextLine();
    }

    private static boolean containsIllegalChars(String input) {
        if (input.contains("<")) return true;
        if (input.contains(">")) return true;
        if (input.contains("\"")) return true;
        if (input.contains("'")) return true;
        if (input.contains("~")) return true;
        if (input.contains(";")) return true;
        if (input.contains("=")) return true;
        if (input.contains("+")) return true;
        if (input.contains("-")) return true;
        if (input.contains("*")) return true;
        if (input.contains("/")) return true;
        if (input.contains("%")) return true;
        if (input.contains("@")) return true;
        if (input.contains(":")) return true;
        if (input.contains("|")) return true;
        if (input.contains("°")) return true;
        if (input.contains("ˇ")) return true;
        if (input.contains("`")) return true;
        if (input.contains("§")) return true;
        if (input.contains("^")) return true;
        if (input.contains("{")) return true;
        return false;
    }
}