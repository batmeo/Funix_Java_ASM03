package ASM02;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Customer extends User{
    private final ArrayList<Account> accounts = new ArrayList<>();
    public Customer(String name, String customerId) {
        super(name, customerId);
    }

    /* In case customer has a premium account at least, so this customer is premium. Otherwise customer is normal
     * 
     */
    public String isPremium() {
        for (Account account : this.accounts) {
            if (account.isPremium())
                return "Premium";
        }
        return " Normal";
    }
    
    /* Only add account once account exist 
     * 
     */
    public void addAccount(Account newAccount) {
        if (!isAccountExist(newAccount.getAccountNumber())) {
            this.accounts.add(newAccount);
        }
    }

    /* Check whether account exist or not. 
     * Return true if account exist 
     * Otherwise return false
     */
    public boolean isAccountExist(String accountNumber) {
        for (Account account : this.accounts) {
            if (accountNumber.equals(account.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

    /* Check valid of customer's ID. Return true if ID is valid 
     * 
     */
    public static boolean checkCustomerId(String customerId) {

        // Check length of ID. 12 is required length
        if (customerId.length() == 12) {
            for (int i = 0; i < customerId.length(); i++) {
                int charNum = customerId.charAt(i);

                // Check character in ID. Only number is allowed
                if (charNum < 48 || charNum > 57) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /* Set customer's ID, those one was validated 
     * 
     */
    @Override
    public void setCustomerId(String customerId) {
        // TODO Auto-generated method stub
        if (checkCustomerId(customerId)) {
            super.setCustomerId(customerId);
        }
    }

    /* Calculate total balance of customer 
     * 
     */
    public double getBalance() {
        double sum = 0;
        for (Account account : accounts) {
            sum += account.getBalance();
        }
        return sum;
    }

    /* Display information of customer: ID, name, total balance and details about their account 
     * 
     */
    public void displayInformation() {
        int i = 1;
        // Calculate space for right align
        String space = "             ";
        System.out.println(this.getCustomerId() + " | " + space.substring(this.getName().length()) + this.getName()
                + " | " + this.isPremium()
                + " | " + space.substring(String.valueOf(format(this.getBalance())).length()) + format(this.getBalance()) + "d");
        for (Account account : this.accounts) {        
            System.out.println(i + "     " + account.toString());
            i++;
        }
    }
    
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    /* Format double number in decimal (Ex: 1,000,000) 
     * 
     */
    protected String format(double number) {
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        return decimalFormat.format(number);
    }
}
