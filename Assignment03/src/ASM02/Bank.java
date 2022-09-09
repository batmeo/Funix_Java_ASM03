package ASM02;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private final String Id =String.valueOf(UUID.randomUUID());
    private final ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer newCustomer) {
        this.customers.add(newCustomer);
    }

    /* Check whether customer exist or not 
     * 
     */
    public boolean isCustomerExist(String customerId) {
        for (Customer customer : this.customers) {
            if (customerId.equals(customer.getCustomerId()))
                return true;
        }
        return false;
    }

    public String getId() {
        return Id;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    
    /* Only add account once customer ID exist 
     * 
     */
    public void addAccount(String customerId, Account account){
        for (Customer customer : this.customers) {
            if (customerId.equals(customer.getCustomerId())) {
                customer.addAccount(account);
            }
        }
    }
    

}
