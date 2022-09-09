package ASM03;

import java.util.Scanner;

import ASM02.*;

public class App {

    private static final String AUTHOR = "FX17512";
    private static final String VERSION = "@V3.0.0";
    private static final Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001234567890";
    private static final String CUSTOMER_NAME = "Doremon";

    /*
     * DIGITAL BANK V3.0.0
     * 1. Show customer information
     * 2. Add Savings account
     * 3. Add Credit account
     * 4. Withdraw
     * 5. Show history of transaction
     * 0. Quit
     */

    public static void main(String[] args) throws Exception {
        activeBank.addCustomer(new DigitalCustomer(CUSTOMER_NAME, CUSTOMER_ID));
        boolean isContinued = true;
        while (isContinued) {
            instruction(AUTHOR, VERSION);
            String quit = sc.nextLine();

            switch (quit) {
                case "1":
                    showCustomer();
                    break;

                case "2":
                    String savingsAccountNumber = validAccountNumber();
                    double savingsBalance = validBalance();
                    System.out.println("Them tai khoan tiet kiem thanh cong!");

                    //Add valid savings account  
                    activeBank.addAccount(CUSTOMER_ID, new SavingAccount(savingsAccountNumber, savingsBalance));
                    break;

                case "3":
                    String loanAccountNumber = validAccountNumber();
                    System.out.println("Them tai khoan tin dung thanh cong!");

                    //Add valid credit account
                    activeBank.addAccount(CUSTOMER_ID, new LoanAccount(loanAccountNumber, 0));
                    break;

                case "4":
                    String accountNumber;
                    double amount;

                    // Input account number before withdrawal
                    do {
                        System.out.println("Nhap so tai khoan can rut tien:");
                        accountNumber = sc.nextLine();
                    } while (!isAccountExist(accountNumber));
                    
                    System.out.println("Nhap so tien can rut: ");
                    amount = sc.nextDouble();
                    sc.nextLine();
                    for (Account account : activeBank.getCustomers().get(0).getAccounts()) {
                        if (account.getAccountNumber().equals(accountNumber)) {

                            // Print out withdrawal recipe
                            account.log(amount) ;
                        }
                    }
                    break;
                case "5":
                    System.out.println("+----------+-------------------------------+----------+");
                    System.out.println("|  LICH SU GIAO DICH                                  |");
                    System.out.println("+----------+-------------------------------+----------+");
                    showCustomer();

                    // Print out transaction history
                    activeBank.getCustomers().get(0).getAccounts().get(0).showHistory();
                    break;
                case "0":
                    isContinued = false;
                    System.out.println("Cam on quy khach da su dung dich vu!");
                    break;
                default:
                    System.out.println("Chuc nang khong ton tai! Vui long nhap lai");
                    break;
            }

        }
    }
    /* Show cusomer information: id, name, class, total balance
     * 
     */

    public static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        }
    }

    /* Input and check valid account number
     * 1. Input account number
     * 2. Length must be 6 char
     * 3. Only number is allowed
     * 4. Check whether account exist
     * 5. Print out a error statement and input until account is valid
     * 6. Return a valid number account 
     */

    public static String validAccountNumber() {
        String accountNumber;
        boolean isValid = false;
        do {
            System.out.println("Nhap ma so tai khoan gom 6 chu so:");
            accountNumber = sc.nextLine();
            if (accountNumber.length() == 6) {
                int count = 0;
                for (int i = 0; i < 6; i++) {
                    // Check every character in account number 
                    if (accountNumber.charAt(i) < 48 || accountNumber.charAt(i) > 57) {
                        System.out.println("So tai khoan khong hop le!");
                        break;
                    } else {
                        // if character is number then count++
                        count++;
                    }
                }
                // Count == 6 means there are 6 valid characters
                if (count == 6) {
                    isValid = true;
                }
            } else {
                System.out.println("So tai khoan khong hop le!");
            }
            for (Customer customer : activeBank.getCustomers()) {
                if (customer.isAccountExist(accountNumber)) {
                    isValid = false;
                    System.out.println("So tai khoan da ton tai!");
                }
            }
        } while (!isValid);
        return accountNumber;
    }

    /* Input and check valid balance
     * 1. Input balance
     * 2. Balance must be greater than 50,000
     * 3. Catch an exception if data input is not a number
     * 4. Print out a error statement and input until balance is valid
     * 5. Return a balance number
     */

    public static double validBalance() {
        boolean isValid = false;
        double balance = 0;
        do {
            try {
                System.out.println("Nhap so du:");
                balance = sc.nextDouble();
                sc.nextLine();
                if (balance >= 50000) {
                    isValid = true;
                } else {
                    System.out.println("So du toi thieu phai tu 50,000d");
                }
            } catch (Exception e) {
                //TODO: handle exception
                sc.nextLine();
                System.out.println("Vui long nhap so du hop le >= 50,000d");
            }
        } while (!isValid);
        return balance;
    }

    /* Check account exist for Withdraw feature
     * Return true if account exist, other wise return false
     */

    public static boolean isAccountExist(String accountNumber) {
        boolean isExist = false;
        for (Customer customer : activeBank.getCustomers()) {
            isExist =  customer.isAccountExist(accountNumber);
        }
        return isExist;
    }

    /* Show instruction
     * Show author and version of this app
     */

    public static void instruction(String author, String version){
        System.out.println("+----------+-------------------------------+----------+");
        System.out.println("| NGAN HANG SO | " + author + version +"                       |");
        System.out.println("+----------+-------------------------------+----------+");
        System.out.println("| 1. Thong tin khach hang                             |");
        System.out.println("| 2. Them tai khoan ATM                               |");
        System.out.println("| 3. Them tai khoan tin dung                          |");
        System.out.println("| 4. Rut tien                                         |");
        System.out.println("| 5. Lich su giao dich                                |");
        System.out.println("| 0. Thoat                                            |");
        System.out.println("+----------+-------------------------------+----------+");
        System.out.print("Chuc nang: ");
    }
}
