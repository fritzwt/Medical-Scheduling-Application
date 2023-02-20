package wesfritzc195pa.SQLMethods;

import javafx.fxml.Initializable;
import wesfritzc195pa.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import wesfritzc195pa.models.Appointment;
import wesfritzc195pa.models.Customer;
import wesfritzc195pa.toolbox.TimeConversions;

import java.sql.*;
import java.time.*;

/**This class talks handles the queries made to the database from the application's Appointment page.
 */
public class AppointmentSQL {

    /**This method creates an observable list filled with all appointment information that is needed for the application.
     * @return Returns a list of all appointment information that pertains to the majority of the application.
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String query = "Select * FROM client_schedule.appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            int customerID = rs.getInt("Customer_ID");
            int staffID = rs.getInt("User_ID");
            int salesRepID = rs.getInt("Contact_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            Timestamp appointmentStartTS = rs.getTimestamp("Start");
            Timestamp appointmentEndTS = rs.getTimestamp("End");
            LocalDateTime appointmentStartLDT = TimeConversions.sqlTimeToLocal(appointmentStartTS);
            LocalDateTime appointmentEndLDT = TimeConversions.sqlTimeToLocal(appointmentEndTS);
            LocalTime appointmentStartLocalTime = appointmentStartLDT.toLocalTime();
            LocalTime appointmentEndLocalTime = appointmentEndLDT.toLocalTime();
            LocalDate date = appointmentStartLDT.toLocalDate();
            Appointment i = new Appointment(appointmentID, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, date, appointmentStartLocalTime, appointmentEndLocalTime,
                    customerID, staffID, salesRepID);
            allAppointments.add(i);
            //SQLException e = new SQLException();
           // e.printStackTrace();
        }
        return allAppointments;
    }

    /**This method creates an observable list that contains information used as a placeholder for appointment information.
     * Not used, but kept in for testing/troubleshooting purposes.
     * @return Returns a list of relevant appointment information.
     * @throws SQLException
     */
    public static ObservableList getAppointmentHolder() throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        String query = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, Type, Start, End, Customer_ID, User_ID FROM client_schedule.appointments AND client_schedule.contacts WHERE appointments.Contact_ID = contacts.Contact_ID;";
    return list;
    }

    /**This is a method that is used for the purposes of adding an appointment object.
     * @param title The title of the appointment.
     * @param description The description of the appointment.
     * @param location The location of the appointment.
     * @param contactID The contact ID attached to the appointment.
     * @param type The type of the appointment that it is.
     * @param date The date that the appointment is scheduled for.
     * @param start The start time of the appointment.
     * @param end The end time of the appointment.
     * @param customerID The customer ID that the appointment is for.
     * @param userID The user ID related to the appointment.
     * @throws SQLException
     */
    public static void addAppointment(String title, String description, String location, int contactID, String type, LocalDate date, Timestamp start, Timestamp end, String customerID, String userID) throws SQLException {
        Connection connection = JDBC.openConnection();

        String query = "INSERT INTO client_schedule.appointments " +
                "(Title, Description, Location, Contact_ID, Type, Start," +
                " End, Customer_ID, User_ID) " +
                "VALUES ('" + title + "', '" + description + "', '" +
                location +"', '" + contactID + "', '" + type + "', '" +
                start +"', '" + end + "', '" + customerID + "' ,'" + userID + "');";
        PreparedStatement ps = JDBC.openConnection().prepareStatement(query);
        ps.execute();
    }

    /**This is a method that is used for the purposes of updating an appointment object.
     * @param appointmentID Appointment ID for the appointment.
     * @param title Title for the appointment.
     * @param description Description of the appointment.
     * @param location Location of the appointment.
     * @param type Type of appointment that it is.
     * @param date Date the appointment is scheduled for.
     * @param start Start time of the appointment.
     * @param end End time of the appointment.
     * @param customerID Customer ID of the customer that has the appointment.
     * @param userID User ID tied to the appointment.
     * @param contactID Contact ID of the provider attached to the appointment.
     * @throws SQLException
     */
    public static void updateAppointment(int appointmentID, String title, String description, String location,
                                         String type, LocalDate date, Timestamp start, Timestamp end, String customerID, int userID,
                                         int contactID) throws SQLException {
        Connection connection = JDBC.openConnection();


        String query = "UPDATE client_schedule.appointments " +
                "SET " +
                "Title = '" + title + "', " +
                "Description = '" + description + "', " +
                "Location = '" + location + "', " +
                "Type = '" + type + "', " +
                "Contact_ID = '" + contactID + "', " +
                "Start = '" + start + "', " +
                "End = '" + end + "', " +
                "Customer_ID = '" + customerID + "'," +
                "User_ID = '" + userID + "'" + " " +
                "WHERE Appointment_ID = '" + appointmentID + "';";
        PreparedStatement ps = JDBC.openConnection().prepareStatement(query);
        ps.executeUpdate();
    }

    /**This method deletes an appointment when selected and confirmed by the user
     * Throws an alert if some type of error exists within the SQL code.
     * @param connection Needs connection to the database to do this function.
     * @param appointmentToDelete The selected appointment that the user wishes to delete.
     * @throws SQLException
     */
    public static void deleteAppointment(Connection connection, ObservableList<Appointment> appointmentToDelete) throws SQLException {
        try {
            for (Appointment appointment : appointmentToDelete) {
                int idOfAppointmentToDelete = appointment.getAppointmentID();
                String query = "DELETE FROM client_schedule.appointments WHERE Appointment_ID= '" +
                        idOfAppointmentToDelete + "';";
                PreparedStatement ps = JDBC.connection.prepareStatement(query);
                ps.execute();

            }
        } catch (SQLException e) {
            if (e != null) {
                Alert sqlError = new Alert(Alert.AlertType.ERROR, "An error in the SQL of this method has occurred, refer to terminal.", ButtonType.OK);
                sqlError.showAndWait();
                e.printStackTrace();
            }
        }

    }



    /**This method is called by the delete Customer method.
     * It should find and delete all appointments for a customer that is being deleted.
     * @param customer , the customer object to be deleted.
     * @return true or false to boolean.
     * */
    public static boolean deleteAllCustomerAppointmentsByCustomerID(Customer customer) throws SQLException {
        int customerID = customer.getCustomerID();
        String query = "DELETE FROM client_schedule.appointments WHERE Customer_ID = " + customerID + ";";
        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ps.execute();
        String query2 = "SELECT * FROM client_schedule.appointments WHERE Customer_ID = " + customerID + ";";
        PreparedStatement ps2 = JDBC.connection.prepareStatement(query2);
        ResultSet rs = ps2.executeQuery();
        ObservableList<Appointment> remainder = FXCollections.observableArrayList();
        while (rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            int cID = rs.getInt("Customer_ID");
            int staffID = rs.getInt("User_ID");
            int salesRepID = rs.getInt("Contact_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            Timestamp appointmentStartTS = rs.getTimestamp("Start");
            Timestamp appointmentEndTS = rs.getTimestamp("End");
            LocalDateTime appointmentStartLDT = TimeConversions.sqlTimeToLocal(appointmentStartTS);
            LocalDateTime appointmentEndLDT = TimeConversions.sqlTimeToLocal(appointmentEndTS);
            LocalTime appointmentStartLocalTime = appointmentStartLDT.toLocalTime();
            LocalTime appointmentEndLocalTime = appointmentEndLDT.toLocalTime();
            LocalDate date = appointmentStartLDT.toLocalDate();
            Appointment i = new Appointment(appointmentID, appointmentTitle, appointmentDescription,
                appointmentLocation, appointmentType, date, appointmentStartLocalTime, appointmentEndLocalTime,
                cID, staffID, salesRepID);
            remainder.add(i);
        }
        if (!remainder.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to Delete Appointments!");
            alert.showAndWait();
            return false;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "All appointments for Customer " + customerID + " have been deleted.");
            alert.showAndWait();
            return true;
            // This works to delete all of a single customer's appointments, but we need to be able to delete the customer immediately after doing this.
        }


    }
}

