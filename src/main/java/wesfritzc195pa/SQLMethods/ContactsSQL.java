package wesfritzc195pa.SQLMethods;

import wesfritzc195pa.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wesfritzc195pa.models.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This class talks handles the queries made to the database to handle the application's contacts.
 */
public class ContactsSQL {

    /**This method creates an observable list of all contacts for other methods to look through.
     * @return Returns a full list of contacts with information dealing with their ID, name, and emails.
     * @throws SQLException
     */
    public static ObservableList<Contact> allContacts() throws SQLException {
        ObservableList <Contact> allContacts = FXCollections.observableArrayList();
        String query = "SELECT * FROM client_schedule.contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Contact_ID = rs.getInt("Contact_ID");
            String Contact_Name = rs.getString("Contact_Name");
            String Contact_Email = rs.getString("Email");
            Contact c = new Contact(Contact_ID, Contact_Name, Contact_Email);
            allContacts.add(c);
        }

        return allContacts;

    }

    /**This method allows a contact ID to be found by looking up their name. Used in the contact combo box.
     * @param contact Who the contact is on the basis of their name.
     * @return Returns the contact ID from looking up their name.
     * @throws SQLException
     */
    public static int getIDbyLookupContactName(String contact) throws SQLException {

        int foundContactID = 0;
        String query = "SELECT Contact_ID FROM client_schedule.contacts WHERE Contact_Name = '" + contact + "';";
        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        foundContactID = rs.getInt("Contact_ID");}
        return foundContactID;
    }

    /**This method allows a contact name to be found by looking up their ID.
     * Not used, but kept in for testing purposes.
     * @param appointmentContactID Who the contact is on the basis of their ID.
     * @return Returns the contact name from lookinig up their ID number.
     * @throws SQLException
     */
    public static String getContactNamebyID(int appointmentContactID) throws SQLException {

        String contactName = "";
        Connection connection = JDBC.openConnection();
        String query = "Select Contact_Name FROM client_schedule.contacts WHERE Contact_ID = '" + appointmentContactID +"';";
        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contactName = rs.getString("Contact_Name");
            return contactName;
        } return contactName;

    }





}
