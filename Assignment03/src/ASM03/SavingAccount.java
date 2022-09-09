package ASM03;

import ASM02.Account;

public class SavingAccount extends Account implements Withdraw {
    
    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    public SavingAccount(String accountNumber, double balance) {
        //TODO Auto-generated constructor stub
        super(accountNumber, balance);

        // Add a transaction to history when new savings account is created.
        this.getHistory().add(new Transaction(this.getAccountNumber(), balance, super.getDateTime(), true));
    }

    /* Withdraw method
     * Return true if withdraw amount is accepted and set the new balance
     * Otherwise return false
     */
    @Override
    public boolean withdraw(double amount) {
        // TODO Auto-generated method stub
        if (isAccepted(amount)) {
            this.setBalance(this.getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }


    /* Check withdrawal amount is accepted or not
     * 1. Amount must be greater than 50,000 and divisible for 10,000
     * 2. The balance after withdraw must be at least 50,000
     * 3. Premium account has unlimited withdrawal amount. Normal account has maximum withdrawal amount per time, it is 5,000,000
     * 4. Return true if amount accepted  
     * 5. Otherwise return false and print out reason
     */
    @Override
    public boolean isAccepted(double amount) {
        // TODO Auto-generated method stub
        if (amount >= 50000 && amount % 10000 == 0) {
            if (this.getBalance() - amount >= 50000) {
                if (isPremium()) {
                    return true;
                } else {
                    if (amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW) {
                        return true;
                    } else {
                        System.out.println("Giao dich that bai! So tien rut toi da moi lan la 5,000,000d");
                        return false;
                    }
                }
            } else {
                System.out.println("So du khong kha dung");
                return false;
            }
        } else {
            System.out.println("So tien rut khong hop le");
            return false;
        }
    }

    /* Print out withdrawal recipe
     * And add a transaction to history
     * Use space.substring for right align 
     */

    @Override
    public void log(double amount) {
        // TODO Auto-generated method stub
        if (withdraw(amount)) {
            // Add withdrawal to history
            this.getHistory().add(new Transaction(this.getAccountNumber(), -amount, super.getDateTime(), true));
            String ATMName = "DIGITAL-BANK-ATM 2022";
            String space = "                                           ";
            System.out.println("+----------+-------------------------------+----------+");
            System.out.println("    BIEN LAI GIAO DICH SAVINGS                       ");
            System.out.println("NGAY G/D:   " + space.substring(super.getDateTime().length()) + super.getDateTime());
            System.out.println("ATM ID:     " + space.substring(ATMName.length()) + ATMName);
            System.out.println("SO TK:      " + space.substring(6) + this.getAccountNumber());
            System.out.println("SO TIEN:    " + space.substring(String.valueOf(super.format(amount)).length() + 1)
                    + super.format(amount) + "d");
            System.out.println("SO DU:      " + space.substring(String.valueOf(super.format(getBalance())).length() + 1)
                    + super.format(getBalance()) + "d");
            System.out.println("PHI + VAT:  " + space.substring(2) + "0d");
            System.out.println("+----------+-------------------------------+----------+");
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String space = "                       ";
        return (super.getAccountNumber() + " | " + "      SAVINGS | " + space.substring(String.valueOf(format(getBalance())).length()) + format(getBalance()) + "d");
    }
    

    
}
