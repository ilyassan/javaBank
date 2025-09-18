import java.util.InputMismatchException;
import java.util.Scanner;
import controllers.MainController;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to YouBank ===");

        while (true) {
            showMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1: MainController.createAccount(); break;
                case 2: MainController.checkBalance(); break;
                case 3: MainController.makeDeposit(); break;
                case 4: MainController.makeWithdrawal(); break;
                case 5: MainController.transferMoney(); break;
                case 6: MainController.viewTransactionHistory(); break;
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
}