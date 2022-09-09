package ASM03;

import ASM02.Bank;
import ASM02.Customer;

public class DigitalBank extends Bank {
  
    /* Return customer if customer ID exist
     * Otherwise return null
     */
    public Customer getCustomerById(String customerId) {
        for (Customer customer : super.getCustomers()) {
            if (customerId.equals(customer.getCustomerId()))
                return customer;
        }
        return null;
    } 
    
}
