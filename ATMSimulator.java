     //                 Task  3                 ATM  Simulator


class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: ₹" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
            return false;
        } else {
            System.out.println("Invalid withdrawal amount!");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Withdrawal failed.");
        }
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + account.getBalance());
    }

    public void displayMenu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }
}

public class ATMSimulator {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000.00);
        ATM atm = new ATM(userAccount);
        atm.displayMenu();
    }
}