package wesfritzc195pa.SQLMethods;

import javafx.scene.layout.Region;
import wesfritzc195pa.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import wesfritzc195pa.models.Country;
import wesfritzc195pa.models.Customer;
import wesfritzc195pa.models.CustomerNames;
import wesfritzc195pa.models.StateProvince;

import java.sql.*;
import java.time.LocalDateTime;

/**This class talks handles the queries made to the database from the application's Customers page.
 */
public class CustomerSQL {

    private static LocalDateTime dt;
    private static PreparedStatement preparedStatement;
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ipAdd = "//127.0.0.1:3306/client_schedule";
    private static final String driverurl = protocol + vendor + ipAdd + "?connectionTimeZone=SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection connection = null;
    private static final String un = "sqlUser";
    private static final String pw = "Passw0rd!";

    /**This method connects the application to the database.
     * @return Returns a connection to the database.
     */
    public static Connection connectToDB() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(driverurl, un, pw);
        } catch (ClassNotFoundException | SQLException e) {
        }
        System.out.println("DB Connection Established!");
        return connection;
    }

    /**This method disconnects the application from the database.
     * Not used currently, but kept in for testing/troubleshooting. purposes.
     */
    public static void disconnectDB() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("DB Connection Closed!");
    }

    /**This method fills the combo box that gives country information.
     * @param connection Needs database connection to work.
     * @return Returns the country Name inside the country combo box.
     * @throws SQLException
     */
    public static ObservableList <Country> selectToFillCountriesComboBox(Connection connection) throws SQLException {
        ObservableList <Country> CountryInfo = FXCollections.observableArrayList();
        String sql = "SELECT Country, Country_ID FROM client_schedule.countries;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String countryName = rs.getString("Country");
            int countryID = rs.getInt("Country_ID");
            Country country = new Country(countryID, countryName);
            CountryInfo.add(country);
        }
        return CountryInfo;
    }

    /**This method creates a list of states/provinces based on the country selected.
     * @param connection Needs a database connection to work.
     * @param countryID Uses the country ID number to perform the filtering.
     * @return Returns a filtered list of states/provinces based on the selected country.
     * @throws SQLException
     */
    public static ObservableList filterStatesByCountry(Connection connection, int countryID) throws
            SQLException {
        ObservableList filteredStates = FXCollections.observableArrayList();

        String sql = "SELECT Division_ID, Division, Country_ID FROM client_schedule.first_level_divisions " +
                "WHERE client_schedule.first_level_divisions.Country_ID =" +
                countryID;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int stateProvinceIDNumber = rs.getInt("Division_ID");
            String stateProvinceName = rs.getString("Division");
            int countryIDNumber = rs.getInt("Country_ID");
            StateProvince stateProvince = new StateProvince(stateProvinceIDNumber, stateProvinceName, countryIDNumber);
            filteredStates.add(stateProvince);
            System.out.println(filteredStates);
        }
        return filteredStates;
    }

    /**This method fills the state/province combo box after being filtered by country.
     * @param connection Needs a database connection to work.
     * @return Returns the filtered names of the states/provinces based on the previously selected country.
     * @throws SQLException
     */
    public static ObservableList selectToFillStateProvinceComboBox(Connection connection) throws SQLException {
        ObservableList StateProvinceInfo = FXCollections.observableArrayList();
        String sql = "SELECT Division, Division_ID, Country_ID FROM client_schedule.first_level_divisions;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int stateProvinceIDNumber = rs.getInt("Division_ID");
            String stateProvinceName = rs.getString("Division");
            int countryIDNumber = rs.getInt("Country_ID");
            StateProvince stateProvince = new StateProvince(stateProvinceIDNumber, stateProvinceName, countryIDNumber);
            StateProvinceInfo.add(stateProvince);
        }
        return StateProvinceInfo;
    }

    /**This method makes an observable list containing all information for the customer table.
     * @param connection Needs a database connection to work.
     * @return Returns a list of customer information for input into the customers table.
     * @throws SQLException
     */
    public static ObservableList <Customer> selectToFillCustomerTable(Connection connection) throws SQLException {
        ObservableList<Customer> CustomerInfo = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, " +
                "Phone, Division, Country, customers.Division_ID, first_level_divisions.Country_ID FROM client_schedule.customers, " +
                "client_schedule.first_level_divisions, client_schedule.countries " +
                "WHERE customers.Division_ID = first_level_divisions.Division_ID AND " +
                "first_level_divisions.Country_ID = countries.Country_ID;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            String customerDivision = rs.getString("Division");
            int customerDivisionID = rs.getInt("Division_ID");
            String customerCountry = rs.getString("Country");
            int customerCountryID = rs.getInt("Country_ID");
            Customer customer = new Customer(customerID, customerName, customerAddress,
                    customerPostalCode, customerPhone, customerDivision, customerDivisionID,
                    customerCountry, customerCountryID);
            CustomerInfo.add(customer);
        }
        return CustomerInfo;
    }

    /**
     * This method checks to see if a customer has an appointment -- if not, they may be deleted.
     * Checks based on their customer ID.
     *
     * @param connection Needs a database connection to properly function.
     * @param customerID Uses the customer ID to search for any existing appointments attached to them.
     * @return Returns a true or false status that correlates to their having an appointment or not.
     * @throws SQLException
     */
    public static boolean lookupIfCustomerAppointments(Connection connection, ObservableList<Customer> customerID) throws SQLException {

        Customer c = customerID.get(0);
        int x = c.getCustomerID();
        ObservableList<Integer> customerAppointments = FXCollections.observableArrayList();
        String query = "SELECT Appointment_ID FROM client_schedule.appointments WHERE Customer_ID = '" + x + "';";
        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int y = 0;
        while (rs.next()) {
            int AppointmentID = rs.getInt("Appointment_ID");
            customerAppointments.add(AppointmentID);
        }
        if (customerAppointments.isEmpty()) {
            return false;
        } else {
            return true;
        }

        }


    /** This method creates an observable list filled with customer names.
     * This list is used for a combo box on the appointments page to pick a customer by ID and name.
     * @return Returns the names and ID numbers of customers.
     * @throws SQLException
     */
    public static ObservableList<Integer> getCustomersByID() throws SQLException {
        ObservableList<Integer> ID = FXCollections.observableArrayList();
     //   ObservableList<CustomerNames> IDNumberForCustomer = FXCollections.observableArrayList();
        String Query = "SELECT * FROM client_schedule.customers;";
        PreparedStatement ps = JDBC.connection.prepareStatement(Query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int customerID = rs.getInt("Customer_ID");
           //String customerName = rs.getString("Customer_Name");
            //CustomerNames cn = new CustomerNames(customerID, customerName);
           // IDNumberForCustomer.add(cn);
            ID.add(customerID);

        }
        //return IDNumberForCustomer;
        return ID;
    }

    /**
     * This method handles the deletion of a customer, if they have no prior appointments.
     *
     * @param connection      Needs a database connection to function properly.
     * @param cutomerToDelete The selected customer in danger of being deleted with user consent.
     * @throws SQLException
     */
    public static void deleteCustomer(Connection connection, ObservableList<Customer> cutomerToDelete) throws SQLException {
        // Create SQL Query to delete a record, you'll need to likely establish the various fields
        // like int CustomerID = customerToDelete.getCustomerID() etcetera
        Customer object = cutomerToDelete.get(0);
        int deletebyID = object.getCustomerID();
        // Fill in the query bit...
        String query = "DELETE FROM client_schedule.customers WHERE Customer_ID = "
                + deletebyID + ";";

        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ps.execute();
        //call the fillCustomerTable function as a new obs list...
        ObservableList<Customer> ComparisonTable = FXCollections.observableArrayList();
        ComparisonTable.addAll(selectToFillCustomerTable(connection));
        //This runs a check to see if the customer we wanted to delete still exists...
        if (ComparisonTable.contains(cutomerToDelete)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Delete Failed.", ButtonType.OK);
            alert.showAndWait();
        } else {
        }
    }

    /**This method is used to update the customer object if they are selected.
     * @param updatedName Customer's name to-be.
     * @param updatedAddress Customer's address to-be.
     * @param updatedPhone Customer's phone number to-be.
     * @param updatedPostal Customer's zip code to-be.
     * @param updatedState Customer's state/provine to-be.
     * @param customerID Customer's ID number.
     * @throws SQLException
     */
    public static void updateCustomer(String updatedName, String updatedAddress, String updatedPhone,
                                      String updatedPostal, int updatedState, int customerID) throws SQLException {

        try {
            String query = "UPDATE client_schedule.customers " +
                    "SET " +
                    "Customer_Name = '" + updatedName + "', " +
                    "Address = '" + updatedAddress + "', " +
                    "Phone = '" + updatedPhone + "', " +
                    "Postal_Code = '" + updatedPostal + "', " +
                    "Division_ID = '" + updatedState + "'" +

                    "WHERE Customer_ID = " + customerID + ";";


            PreparedStatement ps = wesfritzc195pa.helper.JDBC.connection.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "General SQL Error, Check fields for Special Characters and try again");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**this method is used to add a new customer object to the database via application.
     * @param updatedName New customer's name.
     * @param updatedAddress New customer's address.
     * @param updatedPhone New customer's phone number.
     * @param updatedPostal New customer's zip code.
     * @param updatedState New customer's state/province.
     * @throws SQLException
     */
    public static void addCustomer(String updatedName, String updatedAddress, String updatedPhone, String updatedPostal, int updatedState) throws SQLException {
    try {
        String query = "INSERT INTO client_schedule.customers" +
                "(Customer_Name, Address, Phone, Postal_Code, Division_ID)" +
                " VALUES ('" + updatedName +"', '" + updatedAddress + "', '" + updatedPhone + "', '" + updatedPostal +"', '" +
                updatedState + "');" ;
        System.out.println(query);
        PreparedStatement ps = wesfritzc195pa.helper.JDBC.connection.prepareStatement(query);
        ps.executeUpdate();
    } catch (SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "General SQL Error, Check fields for Special Characters and try again");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        e.printStackTrace();

    }


}}

