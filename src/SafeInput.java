import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner in, String prompt, int low, int high) {
        int value;
        while (true) {
            System.out.print(prompt + " [" + low + "-" + high + "]: ");
            if (in.hasNextInt()) {
                value = in.nextInt();
                in.nextLine();
                if (value >= low && value <= high) {
                    return value;
                } else {
                    System.out.println("Input out of range. Enter a number between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid integer. Try again.");
                in.nextLine();
            }
        }
    }

    public static boolean getYNConfirm(Scanner in, String prompt) {
        String response;
        while (true) {
            System.out.print(prompt + " [Y/N]: ");
            response = in.nextLine().trim();
            if (response.equalsIgnoreCase("Y")) return true;
            if (response.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N.");
        }
    }
}
