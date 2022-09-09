package ASM02;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ASM03.ReportService;
import ASM03.Transaction;

public class Account implements ReportService{
    private String accountNumber;
    private double balance;
    private static final List<Transaction> history = new ArrayList<Transaction>();
    
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /* Account has more than 10,000,000 in balance is premium 
     * 
     */
    public boolean isPremium() {
        if (this.balance >= 10000000) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        // Calculate space for right align
        String space = "                                       ";
        String printedSpace = space.substring(1, space.length() - format(balance).length());
        return (accountNumber + " | " + printedSpace + format(balance)+"d");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    
    
    /* Format double number in decimal (Ex: 1,000,000) 
     * 
     */
    protected static String format(double number) {
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        return decimalFormat.format(number);
    }

    /* Return current date and time 
     * 
     */

    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    @Override
    public void log(double amount) {
        // TODO Auto-generated method stub

    }

    public List<Transaction> getHistory() {
        return history;
    }

    /* Print out history of transaction
     * Latest transaction is on top
     * space.substring for right align
     */
    public void showHistory() {
        String space = "              ";
        for (int i = history.size()-1; i >= 0; i--) {
            System.out.println("      " + history.get(i).getAccountNumber() + " | "
                    + space.substring(String.valueOf(format(history.get(i).getAmount())).length())
                    + format(history.get(i).getAmount()) + "d | " + history.get(i).getTime());
        }
        System.out.println("+----------+-------------------------------+----------+");
    }


    
}
