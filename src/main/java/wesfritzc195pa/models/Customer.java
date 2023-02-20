package wesfritzc195pa.models;



/**Class that holds information pertaining to creating customer objects.*/
public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String customerStateProvince;
    private int customerStateProvinceID;
    private String customerCountry;

    private int customerCountryID;

    /**Method that retrieves the state/province ID number for the customer.
     * @return the state/province ID number for the customer.
     */
    public int getCustomerStateProvinceID() {
        return customerStateProvinceID;
    }

    /**Method that sets the customer's state/province.
     * Unused due to database involvement.
     * @param customerStateProvinceID the customer's state/province ID number.*/
    public void setCustomerStateProvinceID(int customerStateProvinceID) {
        this.customerStateProvinceID = customerStateProvinceID;
    }

    /**Method that retrieves the country ID number for the customer.
     * @return the country ID number for the customer.
     */
    public int getCustomerCountryID() {
        return customerCountryID;
    }

    /**Method that sets the customer's ID number.
     * Unused due to database involvement.
     * @param customerCountryID the customer's country ID number.*/
    public void setCustomerCountryID(int customerCountryID) {
        this.customerCountryID = customerCountryID;
    }

    /**Method that retrieves the customer ID number, unique to each customer.
     * @return the customer ID number.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**Method that sets the customer's ID number, unique to each customer.
     * Unused due to database involvement.
     * @param customerID the customer's ID number.*/
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**Method that retrieves the customer's name.
     * @return the customer's name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**Method that sets the customer's name.
     * Unused due to database involvement.
     * @param customerName the customer's name.*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**Method that retrieves the customer's address.
     * @return the customer's address.
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**Method that sets the customer's address.
     * Unused due to database involvement.
     * @param customerAddress the customer's address.*/
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**Method that retrieves the customer's zip code.
     * @return the customer's zip code.
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**Method that sets the customer's zip code.
     * Unused due to database involvement.
     * @param customerPostalCode the customer's zip code.*/
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**Method that retrieves the customer's phone number.
     * @return the customer's phone number.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**Method that sets the customer's phone number.
     * Unused due to database involvement.
     * @param customerPhone the customer's phone number.*/
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**Method that retrieves the state/province name.
     * @return the state/province name.
     */
    public String getCustomerStateProvince() {
        return customerStateProvince;
    }

    /**Method that sets the customer's state/province name.
     * Unused due to database involvement.
     * @param customerStateProvince the customer's state/province name.*/
    public void setCustomerStateProvince(String customerStateProvince) {
        this.customerStateProvince = customerStateProvince;
    }

    /**Method that retrieves the customer's country.
     * @return the customer's country.
     */
    public String getCustomerCountry() {
        return customerCountry;
    }

    /**Method that sets the customer's country.
     * Unused due to database involvement.
     * @param customerCountry the customer's country.*/
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    /**This is the method that is invoked when creating or modifying a customer object.
     * @param customerID ID number of the customer.
     * @param customerName Name of the customer.
     * @param customerAddress Address of the customer.
     * @param customerPostalCode Postal code of the customer.
     * @param customerPhone Phone number of the customer.
     * @param customerStateProvince State/Province of the customer.
     * @param customerStateProvinceID State/Province ID of the customer.
     * @param customerCountry Country of residence for the customer.
     * @param customerCountryID Country ID of the customer's country.
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerPostalCode,
                    String customerPhone, String customerStateProvince, int customerStateProvinceID, String customerCountry, int customerCountryID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerStateProvince = customerStateProvince;
        this.customerStateProvinceID = customerStateProvinceID;
        this.customerCountry = customerCountry;
        this.customerCountryID = customerCountryID;
    }


}