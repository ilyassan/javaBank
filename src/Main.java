import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    final private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== Welcome to YouBank ===");

        while (true) {
            showMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7:
                    System.out.println("Thank you for using YouBank!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Create Account");
        System.out.println("2. Check Balance");
        System.out.println("3. Make Deposit");
        System.out.println("4. Make Withdrawal");
        System.out.println("5. Transfer Money");
        System.out.println("6. View Transaction History");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createAccount() {
        System.out.println("\n=== CREATE ACCOUNT ===");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose account type: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                pauseBeforeMenu();
                break;
            case 2:
                pauseBeforeMenu();
                break;
            case 3: return;
            default: System.out.println("Invalid option.");
        }
    }

    private static void checkBalance() {
        System.out.println("\n=== CHECK BALANCE ===");
        System.out.println("1. Search by Account Code");
        System.out.println("2. List All Accounts to Select");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Enter account code (CPT-XXXXX): ");
                break;
            case 2:
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option.");
                pauseBeforeMenu();
                return;
        }

        pauseBeforeMenu();
    }

    private static int getIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a whole number.");
            return -1;
        }
    }

    private static double getDoubleInput() {
        try {
            double input = scanner.nextDouble();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private static void pauseBeforeMenu() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}