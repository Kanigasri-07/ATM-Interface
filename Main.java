import java.util.Scanner;

// Requirement 4: Create a class to represent the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the account balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0; // Avoid negative initial balances
        }
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Requirement 3: Method to deposit money into the bank account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            // Requirement 7: Display success message
            System.out.printf("Successfully deposited: $%.2f%n", amount);
        } else {
            // Requirement 6 & 7: Validate and display error message
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    // Requirement 3: Method to withdraw money from the bank account
    public boolean withdraw(double amount) {
        // Requirement 6: Validate user input limits (positive check)
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
            return false;
        }
        
        // Requirement 6: Validate sufficient balance limits
        if (amount <= balance) {
            balance -= amount;
            // Requirement 7: Display success message
            System.out.printf("Successfully withdrew: $%.2f%n", amount);
            return true;
        } else {
            // Requirement 7: Display failure message due to insufficient balance
            System.out.println("Error: Transaction failed. Insufficient account balance.");
            return false;
        }
    }
}

// Requirement 1: Create a class to represent the ATM machine
class ATM {
    // Requirement 5: Connect the ATM class with the BankAccount class
    private BankAccount userAccount;
    private Scanner scanner;

    // Constructor accepts a BankAccount instance to link them together
    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    // Requirement 2: Design the user interface and display menu options
    public void start() {
        int choice;
        do {
            System.out.println("\n=================================");
            System.out.println("       AUTOMATED TELLER MACHINE  ");
            System.out.println("=================================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit Machine");
            System.out.print("Please choose an option (1-4): ");
            
            // Check for valid integer input
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                performAction(choice);
            } else {
                System.out.println("Invalid entry. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input from scanner buffer
                choice = -1;
            }
        } while (choice != 4);
    }

    // Helper method to process choice inputs
    private void performAction(int choice) {
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                handleDeposit();
                break;
            case 3:
                handleWithdraw();
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("Invalid option selected. Try again.");
        }
    }

    // Requirement 3: Implementation of checkBalance option
    private void checkBalance() {
        // Requirement 7: Display balance message to the user
        System.out.printf("Your current balance is: $%.2f%n", userAccount.getBalance());
    }

    // Interactive helper for depositing
    private void handleDeposit() {
        System.out.print("Enter deposit amount: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            userAccount.deposit(amount); // Modifies the linked balance
        } else {
            System.out.println("Error: Invalid numeric input.");
            scanner.next(); // Clear scanner buffer
        }
    }

    // Interactive helper for withdrawing
    private void handleWithdraw() {
        System.out.print("Enter withdrawal amount: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            userAccount.withdraw(amount); // Modifies the linked balance
        } else {
            System.out.println("Error: Invalid numeric input.");
            scanner.next(); // Clear scanner buffer
        }
    }
}

// Execution Main Class
public class Main {
    public static void main(String[] args) {
        // Instantiating a bank account with an initial balance of $500.00
        BankAccount myAccount = new BankAccount(500.00);
        
        // Passing the account instance to the ATM machine to link them together
        ATM machine = new ATM(myAccount);
        
        // Booting up the user interface
        machine.start();
    }
}
