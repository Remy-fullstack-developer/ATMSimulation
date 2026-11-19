import java.util.Scanner;

public class ATMSimulation {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Data 
        final int CORRECT_PIN = 1234;     
        final int MAX_ATTEMPTS = 3;       
        double balance = 1000.00;       

        String[] transactions = new String[100];  
        int transactionCount = 0;                 

        //  1. Authentication 
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < MAX_ATTEMPTS && !loggedIn) {
            System.out.print("Enter your PIN: ");
            int pin = input.nextInt();

            if (pin == CORRECT_PIN) {
                loggedIn = true;
                System.out.println("Login successful!\n");
            } else {
                attempts++;
                System.out.println("Wrong PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Too many wrong attempts. Card blocked.");
            input.close();
            return;   
        }

        //  2. Main menu
        int choice = 0;
        String result = "";   

        while (choice != 5) {
            System.out.println("===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.println("--------------------");

            // Show the result of the last action below the menu
            if (!result.equals("")) {
                System.out.println(result);
            }

            System.out.print("Choose an option: ");
            choice = input.nextInt();
            result = "";     

            if (choice == 1) {
                
                // Check balance
                result = "Your balance is: $" + balance + "\n";

            } else if (choice == 2) {
                
                // Deposit
                System.out.print("Enter amount to deposit: ");
                double amount = input.nextDouble();

                if (amount <= 0) {
                    result = "Invalid amount.\n";
                } else if (transactionCount >= transactions.length) {
                    result = "Transaction history is full.\n";
                } else {
                    balance = balance + amount;
                    transactions[transactionCount] = "Deposit: +$" + amount;
                    transactionCount++;
                    result = "Deposit successful. New balance: $" + balance + "\n";
                }

            } else if (choice == 3) {
                // Withdraw
                System.out.print("Enter amount to withdraw: ");
                double amount = input.nextDouble();

                if (amount <= 0) {
                    result = "Invalid amount.\n";
                } else if (amount > balance) {
                    result = "Error: Not enough money in your account.\n";
                } else if (transactionCount >= transactions.length) {
                    result = "Transaction history is full.\n";
                } else {
                    balance = balance - amount;
                    transactions[transactionCount] = "Withdrawal: -$" + amount;
                    transactionCount++;
                    result = "Withdrawal successful. New balance: $" + balance + "\n";
                }

            } else if (choice == 4) {
                
                // View transactions
                result = "--- Transaction History ---\n";
                if (transactionCount == 0) {
                    result = result + "No transactions yet.\n";
                } else {
                    for (int i = 0; i < transactionCount; i++) {
                        result = result + (i + 1) + ". " + transactions[i] + "\n";
                    }
                }

            } else if (choice == 5) {
                
                // Exit
                
                System.out.println("Thank you for using the ATM. Goodbye!");

            } else {
                result = "Invalid option. Try again.\n";
            }
        }

        input.close();
    }
}
