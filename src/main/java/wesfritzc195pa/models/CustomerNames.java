package wesfritzc195pa.models;

/**
 * A class that makes customer objects to be called in Appointments.
 * Works in a combo box that selects customers based on available names and ID numbers.
 */
public class CustomerNames {

    /**Method to get the customer IDs to be used in the combo box.
     * @return Returns the Customer ID.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**Method to set the customer IDs to be used in the combo box.
     * @param customerID The ID number for the customer in question.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**Method to get the name of the customer for the combo box.
     * Unused, because the name is gotten by the customer ID instead.
     * @return Returns the customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**Method to set the customer's name for the combo box.
     * Unused, due to database involvement.
     * @param customerName The name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private int customerID;
    private String customerName;

    /**This method is creates the objects called CustomerNames.
     * Consists of the customer's name and ID to be used to fill a combo box.
     * @param customerID The ID number of the customer.
     * @param customerName The name of the customer.
     */
    public CustomerNames(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

}
