import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class BankY {
    private static Bank bank = new Bank();
    private static final String DATA_FILE = "bank_data.ser";

    public static void main(String[] args) {
        loadBankData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBankY Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> createAccount(scanner);
                    case 2 -> depositFunds(scanner);
                    case 3 -> withdrawFunds(scanner);
                    case 4 -> transferFunds(scanner);
                    case 5 -> checkBalance(scanner);
                    case 6 -> {
                        saveBankData();
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.next();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        bank.createAccount(accountNumber, accountHolderName, initialBalance);
        System.out.println("Account created successfully.");
    }

    private static void depositFunds(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawFunds(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            try {
                account.withdraw(amount);
                System.out.println("Withdrawal successful. New balance: " + account.getBalance());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transferFunds(Scanner scanner) {
        System.out.print("Enter source account number: ");
        String fromAccount = scanner.next();
        System.out.print("Enter destination account number: ");
        String toAccount = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        try {
            bank.transferFunds(fromAccount, toAccount, amount);
            System.out.println("Transfer successful.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();

        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void saveBankData() {
        try {
            DataPersistence.saveAccounts(bank.getAccounts(), DATA_FILE);
            System.out.println("Bank data saved.");
        } catch (IOException e) {
            System.out.println("Error saving bank data: " + e.getMessage());
        }
    }

    private static void loadBankData() {
        try {
            bank = new Bank();
            Map<String, Account> loadedAccounts = DataPersistence.loadAccounts(DATA_FILE);
            bank.getAccounts().putAll(loadedAccounts);
            System.out.println("Bank data loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing bank data found. Starting with an empty bank.");
        }
    }
}
