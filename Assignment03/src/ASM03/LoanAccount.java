package ASM03;

import ASM02.Account;

public class LoanAccount extends Account implements Withdraw {
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    private double fee = 0;

    public LoanAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /* Calculate fee when withdraw from credit account
     * If amount greater than 10,000,000, fee is 1%. Otherwise fee is 5%
     * Return a number of fee
     */
    private double fee(double amount) {
        return amount * (this.getBalance() >= 10000000 ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE);
    }

    /* Withdraw method
     * Return true if withdraw amount is accepted
     * Otherwise return false
     */
    @Override
    public boolean withdraw(double amount) {
        // TODO Auto-generated method stub
        if (isAccepted(amount)) {
            fee = fee(amount);
            double newBalance = this.getBalance() + amount + fee;
            this.setBalance(newBalance);
            this.getHistory().add(new Transaction(this.getAccountNumber(), -amount, super.getDateTime(), true));
            return true;
        } else
            return false;
    }

    /* Check withdrawal amount is accepted or not
     * 1. Amount must be greater than 50,000 and divisible for 10,000
     * 2. Limit is 100,000,000
     * 3. Minimum limit is 50,000 
     * 4. Return true if amount accepted  
     * 5. Otherwise return false and print out reason
     */
    @Override
    public boolean isAccepted(double amount) {
        // TODO Auto-generated method stub
        if (amount >= 50000 && amount % 10000 == 0) {
            if (this.getBalance() + amount + fee(amount) + 50000 <= LOAN_ACCOUNT_MAX_BALANCE) {
                return true;
            } else {
                System.out.println("So tien da vuot qua han muc con lai!");
                return false;
            }
        } else {
            System.out.println("So tien rut khong hop le");
            return false;
        }
    }

    /* Print out withdrawal recipe
     * Set new balance 
     * And add a transaction to history
     * Use space.substring for right align 
     */
    @Override
    public void log(double amount) {
        // TODO Auto-generated method stub
        if (withdraw(amount)) {
            String ATMName = "DIGITAL-BANK-ATM 2022";
            String space = "                                          ";
            System.out.println("+----------+-------------------------------+----------+");
            System.out.println("    BIEN LAI GIAO DICH SAVINGS                       ");
            System.out.println("NGAY G/D:   " + space.substring(super.getDateTime().length()) + super.getDateTime());
            System.out.println("ATM ID:     " + space.substring(ATMName.length()) + ATMName);
            System.out.println("SO TK:      " + space.substring(6) + this.getAccountNumber());
            System.out.println("SO TIEN:    " + space.substring(String.valueOf(super.format(amount)).length() + 1)
                    + super.format(amount) + "d");
            System.out.println("DU NO:      " + space.substring(String.valueOf(super.format(getBalance())).length() + 1)
                    + super.format(getBalance()) + "d");
            System.out.println("PHI + VAT:  " + space.substring(String.valueOf(super.format(fee)).length() + 1) + super.format(fee) + "d");
            System.out.println("+----------+-------------------------------+----------+");
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String space = "                       ";
        return (super.getAccountNumber() + " | " + "         LOAN | " + space.substring(String.valueOf(format(getBalance())).length()) + format(getBalance())+"d");
    }
    

    
}
