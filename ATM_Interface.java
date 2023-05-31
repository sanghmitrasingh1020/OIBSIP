import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

class User {
    private String userId;
    private String pin;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }
}

class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class Transaction {
    private String transactionId;
    private Date date;
    private String type;
    private double amount;
    private String fromAccount;
    private String toAccount;

    public Transaction(String transactionId, Date date, String type, double amount, String fromAccount,
            String toAccount) {
        this.transactionId = transactionId;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }
}

class ATM {
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public ATM() {
        users = new ArrayList<>();
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public Account getAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void showTransactionsHistory(String accountNumber) {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (Transaction transaction : transactions) {
            if (transaction.getFromAccount().equals(accountNumber)
                    || transaction.getToAccount().equals(accountNumber)) {
                System.out.println("Transaction ID: " + transaction.getTransactionId());
                System.out.println("Date: " + transaction.getDate());
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("From Account: " + transaction.getFromAccount());
                System.out.println("To Account: " + transaction.getToAccount());
                System.out.println("---------------------");
            }
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account != null) {
            double balance = account.getBalance();
            if (balance >= amount) {
                account.setBalance(balance - amount);
                Transaction transaction = new Transaction("T" + (transactions.size() + 1), new Date(), "Withdraw",
                        amount, accountNumber, null);
                addTransaction(transaction);
                System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void deposit(String accountNumber, double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account != null) {
            double balance = account.getBalance();
            account.setBalance(balance + amount);
            Transaction transaction = new Transaction("T" + (transactions.size() + 1), new Date(), "Deposit", amount,
                    null, accountNumber);
            addTransaction(transaction);
            System.out.println("Deposit successful. Current balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = getAccountByNumber(fromAccountNumber);
        Account toAccount = getAccountByNumber(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            double fromBalance = fromAccount.getBalance();
            if (fromBalance >= amount) {
                fromAccount.setBalance(fromBalance - amount);
                double toBalance = toAccount.getBalance();
                toAccount.setBalance(toBalance + amount);
                Transaction transaction = new Transaction("T" + (transactions.size() + 1), new Date(), "Transfer",
                        amount, fromAccountNumber, toAccountNumber);
                addTransaction(transaction);
                System.out.println("Transfer successful. Current balance in " + fromAccountNumber + ": "
                        + fromAccount.getBalance());
                System.out.println("Current balance in " + toAccountNumber + ": " + toAccount.getBalance());
            } else {
                System.out.println("Insufficient balance in " + fromAccountNumber);
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }
}

public class ATM_Interface {
    public static void main(String[] args) {
        ATM atm = new ATM();

        User user1 = new User("user01", "1235");
        User user2 = new User("user02", "5648");
        atm.addUser(user1);
        atm.addUser(user2);

        Account account1 = new Account("A143", 60000);
        Account account2 = new Account("B450", 10000);
        atm.addAccount(account1);
        atm.addAccount(account2);

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To The Super ATM!");
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter User PIN: ");
        String pin = sc.nextLine();

        User currentUser = atm.getUserById(userId);
        if (currentUser != null && currentUser.getPin().equals(pin)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("---------------------");
                System.out.println("Choose an option:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Enter option number: ");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Enter account number: ");
                        String accountNumber = sc.next();
                        atm.showTransactionsHistory(accountNumber);
                        break;
                    case 2:
                        System.out.print("Enter account number: ");
                        accountNumber = sc.next();
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = sc.nextDouble();
                        atm.withdraw(accountNumber, withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter account number: ");
                        accountNumber = sc.next();
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        atm.deposit(accountNumber, depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter source account number: ");
                        String fromAccountNumber = sc.next();
                        System.out.print("Enter destination account number: ");
                        String toAccountNumber = sc.next();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = sc.nextDouble();
                        atm.transfer(fromAccountNumber, toAccountNumber, transferAmount);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }
    }
}