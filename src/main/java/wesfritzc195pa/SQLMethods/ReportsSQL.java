package wesfritzc195pa.SQLMethods;



import wesfritzc195pa.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wesfritzc195pa.models.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import wesfritzc195pa.toolbox.TimeConversions;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**This class talks handles the queries made to the database from the application's Reports page.
 */
public class ReportsSQL {

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

    /**Method to open connection to the Database.
     * Not used.
     * Left in for contingency/testing purposes.
     * @return Returns an open connection to the database.
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

    /**Method to close connection to the Database.
     * Not used.
     * Left in for contingency/testing purposes.
     */
    public static void disconnectDB() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("DB Connection Closed!");
    }

    /**Method that creates an observable list of appointments for the month of January.
     * @param connection Needs connection to the database to work.
     * @return Returns January appointments.
     * @throws SQLException
     */
    public static ObservableList janFilterAppointments (Connection connection) throws SQLException {
        ObservableList janAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 1;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();

       while (rs.next()) {

           int x = rs.getInt (1);
           janAppointments.add(x);
       }
       return janAppointments;
      }

    /**Method that creates an observable list of appointments for the month of February.
     * @param connection Needs connection to the database to work.
     * @return Returns February appointments.
     * @throws SQLException
     */
    public static ObservableList febFilterAppointments (Connection connection) throws SQLException {
        ObservableList febAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 2;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            febAppointments.add(x);
        }
        return febAppointments;
    }

    /**Method that creates an observable list of appointments for the month of March.
     * @param connection Needs connection to the database to work.
     * @return Returns March appointments.
     * @throws SQLException
     */
    public static ObservableList marFilterAppointments (Connection connection) throws SQLException {
        ObservableList marAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 3;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            marAppointments.add(x);
        }
        return marAppointments;
    }

    /**Method that creates an observable list of appointments for the month of April.
     * @param connection Needs connection to the database to work.
     * @return Returns April appointments.
     * @throws SQLException
     */
    public static ObservableList aprFilterAppointments (Connection connection) throws SQLException {
        ObservableList aprAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 4;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            aprAppointments.add(x);
        }
        return aprAppointments;
    }

    /**Method that creates an observable list of appointments for the month of May.
     * @param connection Needs connection to the database to work.
     * @return Returns May appointments.
     * @throws SQLException
     */
      public static ObservableList mayFilterAppointments (Connection connection) throws SQLException {
        ObservableList mayAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 5;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();

       while (rs.next()) {

           int x = rs.getInt (1);
           mayAppointments.add(x);
       }
       return mayAppointments;
      }

    /**Method that creates an observable list of appointments for the month of June.
     * @param connection Needs connection to the database to work.
     * @return Returns June appointments.
     * @throws SQLException
     */
    public static ObservableList junFilterAppointments (Connection connection) throws SQLException {
        ObservableList junAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 6;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            junAppointments.add(x);
        }
        return junAppointments;
    }

    /**Method that creates an observable list of appointments for the month of July.
     * @param connection Needs connection to the database to work.
     * @return Returns July appointments.
     * @throws SQLException
     */
    public static ObservableList julFilterAppointments (Connection connection) throws SQLException {
        ObservableList julAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 7;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            julAppointments.add(x);
        }
        return julAppointments;
    }

    /**Method that creates an observable list of appointments for the month of August.
     * @param connection Needs connection to the database to work.
     * @return Returns August appointments.
     * @throws SQLException
     */
    public static ObservableList augFilterAppointments (Connection connection) throws SQLException {
        ObservableList augAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 8;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            augAppointments.add(x);
        }
        return augAppointments;
    }

    /**Method that creates an observable list of appointments for the month of September.
     * @param connection Needs connection to the database to work.
     * @return Returns September appointments.
     * @throws SQLException
     */
    public static ObservableList sepFilterAppointments (Connection connection) throws SQLException {
        ObservableList sepAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 9;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            sepAppointments.add(x);
        }
        return sepAppointments;
    }

    /**Method that creates an observable list of appointments for the month of October.
     * @param connection Needs connection to the database to work.
     * @return Returns October appointments.
     * @throws SQLException
     */
    public static ObservableList octFilterAppointments (Connection connection) throws SQLException {
        ObservableList octAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 10;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            octAppointments.add(x);
        }
        return octAppointments;
    }

    /**Method that creates an observable list of appointments for the month of November.
     * @param connection Needs connection to the database to work.
     * @return Returns November appointments.
     * @throws SQLException
     */
    public static ObservableList novFilterAppointments (Connection connection) throws SQLException {
        ObservableList novAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 11;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            novAppointments.add(x);
        }
        return novAppointments;
    }

    /**Method that creates an observable list of appointments for the month of December.
     * @param connection Needs connection to the database to work.
     * @return Returns December appointments.
     * @throws SQLException
     */
    public static ObservableList decFilterAppointments (Connection connection) throws SQLException {
        ObservableList decAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(EXTRACT(MONTH FROM Start)) FROM client_schedule.appointments " +
                "WHERE EXTRACT(MONTH FROM Start) = 12;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            decAppointments.add(x);
        }
        return decAppointments;
    }

    /**Method that creates an observable list of appointments pertaining to the type "Planning Session."
     * @param connection Needs connection to the database to work.
     * @return Returns list of Planning Session appointments.
     * @throws SQLException
     */
    public static ObservableList numberPAppointments (Connection connection) throws SQLException {
        ObservableList pAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(Type) FROM client_schedule.appointments " +
                "WHERE Type = 'Planning Session';";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            pAppointments.add(x);
        }
        return pAppointments;
    }

    /**Method that creates an observable list of appointments pertaining to the type "De-Briefing."
     * @param connection Needs connection to the database to work.
     * @return Returns list of De-Briefing appointments.
     * @throws SQLException
     */
    public static ObservableList numberDBAppointments (Connection connection) throws SQLException {
        ObservableList dbAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(Type) FROM client_schedule.appointments " +
                "WHERE Type = 'De-briefing';";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            dbAppointments.add(x);
        }
        return dbAppointments;
    }

    /**Method that creates an observable list of appointments pertaining to the type "Other."
     * @param connection Needs connection to the database to work.
     * @return Returns list of Other appointments.
     * @throws SQLException
     */
    public static ObservableList numberOTRAppointments (Connection connection) throws SQLException {
        ObservableList otrAppointments = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(Type) FROM client_schedule.appointments " +
                "WHERE Type != 'Planning Session'" +
                " AND Type != 'De-briefing';";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            otrAppointments.add(x);
        }
        return otrAppointments;
    }

    /**Method that creates an observable list of customers residing in the US.
     * @param connection Needs connection to the database to work.
     * @return Returns list of customers in the US.
     * @throws SQLException
     */
    public static ObservableList numberUSCustomers (Connection connection) throws SQLException {
        ObservableList usCustomers = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(customers.Division_ID) FROM client_schedule.customers, client_schedule.first_level_divisions" +
                " WHERE customers.Division_ID = first_level_divisions.Division_ID" +
                " AND first_level_divisions.Country_ID = 1;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            usCustomers.add(x);
        }
        return usCustomers;
    }

    /**Method that creates an observable list of customers residing in the UK.
     * @param connection Needs connection to the database to work.
     * @return Returns list of customers in the UK.
     * @throws SQLException
     */
    public static ObservableList numberUKCustomers (Connection connection) throws SQLException {
        ObservableList ukCustomers = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(customers.Division_ID) FROM client_schedule.customers, client_schedule.first_level_divisions" +
                " WHERE customers.Division_ID = first_level_divisions.Division_ID" +
                " AND first_level_divisions.Country_ID = 2;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            ukCustomers.add(x);
        }
        return ukCustomers;
    }

    /**Method that creates an observable list of customers residing in Canada.
     * @param connection Needs connection to the database to work.
     * @return Returns list of customers in the Canada.
     * @throws SQLException
     */
    public static ObservableList numberCanadaCustomers (Connection connection) throws SQLException {
        ObservableList canadaCustomers = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(customers.Division_ID) FROM client_schedule.customers, client_schedule.first_level_divisions" +
                " WHERE customers.Division_ID = first_level_divisions.Division_ID" +
                " AND first_level_divisions.Country_ID = 3;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int x = rs.getInt (1);
            canadaCustomers.add(x);
        }
        return canadaCustomers;
    }

    /**Method that creates an observable list of appointments for contact Anika.
     * @param connection Needs connection to the database to work.
     * @return Returns list of appointments for contact Anika.
     * @throws SQLException
     */
    public static ObservableList<Appointment> fillAnikaTable(Connection connection) throws SQLException {
        ObservableList<Appointment> aClientTables = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client_schedule.appointments WHERE Contact_ID = 1;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentType = rs.getString("Type");
           // String appointmentStartDT = rs.getString("Start");
//            LocalTime appointmentStartDT = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
//            LocalTime appointmentEndDT = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
            Timestamp appointmentStartTS = rs.getTimestamp("Start");
            Timestamp appointmentEndTS = rs.getTimestamp("End");
            LocalDateTime appointmentStartLDT = TimeConversions.sqlTimeToLocal(appointmentStartTS);
            LocalDateTime appointmentEndLDT = TimeConversions.sqlTimeToLocal(appointmentEndTS);
            LocalTime appointmentStartLocalTime = appointmentStartLDT.toLocalTime();
            LocalTime appointmentEndLocalTime = appointmentEndLDT.toLocalTime();
            LocalDate datePicker = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
            //String appointmentEndDT = rs.getString("End");
            String appointmentLocation = rs.getString("Location");
            int appointmentCustomerID = rs.getInt("Customer_ID");
            int appointmentStaffID = rs.getInt("User_ID");
            int appointmentContactID = rs.getInt("Contact_ID");

            Appointment appointment = new Appointment( appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType,
                    datePicker, appointmentStartLocalTime, appointmentEndLocalTime, appointmentCustomerID, appointmentStaffID, appointmentContactID);
                    aClientTables.add(appointment);
        }
        System.out.println(aClientTables);
        return aClientTables;
    }

    /**Method that creates an observable list of appointments for contact Daniel.
     * @param connection Needs connection to the database to work.
     * @return Returns list of appointments for contact Daniel.
     * @throws SQLException
     */
    public static ObservableList<Appointment> fillDanielTable(Connection connection) throws SQLException {
        ObservableList<Appointment> dClientTables = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client_schedule.appointments WHERE Contact_ID = 2;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentType = rs.getString("Type");
            // String appointmentStartDT = rs.getString("Start");
//            LocalTime appointmentStartDT = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
//            LocalTime appointmentEndDT = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
            Timestamp appointmentStartTS = rs.getTimestamp("Start");
            Timestamp appointmentEndTS = rs.getTimestamp("End");
            LocalDateTime appointmentStartLDT = TimeConversions.sqlTimeToLocal(appointmentStartTS);
            LocalDateTime appointmentEndLDT = TimeConversions.sqlTimeToLocal(appointmentEndTS);
            LocalTime appointmentStartLocalTime = appointmentStartLDT.toLocalTime();
            LocalTime appointmentEndLocalTime = appointmentEndLDT.toLocalTime();LocalDate datePicker = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
            //String appointmentEndDT = rs.getString("End");
            String appointmentLocation = rs.getString("Location");
            int appointmentCustomerID = rs.getInt("Customer_ID");
            int appointmentStaffID = rs.getInt("User_ID");
            int appointmentContactID = rs.getInt("Contact_ID");

            Appointment appointment = new Appointment( appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType,
                    datePicker, appointmentStartLocalTime, appointmentEndLocalTime, appointmentCustomerID, appointmentStaffID, appointmentContactID);
                    dClientTables.add(appointment);
        }
        return dClientTables;
    }

    /**Method that creates an observable list of appointments for contact Li.
     * @param connection Needs connection to the database to work.
     * @return Returns list of appointments for contact Li.
     * @throws SQLException
     */
    public static ObservableList<Appointment> fillLiTable(Connection connection) throws SQLException {
        ObservableList<Appointment> lClientTables = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client_schedule.appointments WHERE Contact_ID = 3;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentType = rs.getString("Type");
                // String appointmentStartDT = rs.getString("Start");
//                LocalTime appointmentStartDT = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
//                LocalTime appointmentEndDT = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
                Timestamp appointmentStartTS = rs.getTimestamp("Start");
                Timestamp appointmentEndTS = rs.getTimestamp("End");
                LocalDateTime appointmentStartLDT = TimeConversions.sqlTimeToLocal(appointmentStartTS);
                LocalDateTime appointmentEndLDT = TimeConversions.sqlTimeToLocal(appointmentEndTS);
                LocalTime appointmentStartLocalTime = appointmentStartLDT.toLocalTime();
                LocalTime appointmentEndLocalTime = appointmentEndLDT.toLocalTime();



                LocalDate datePicker = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
                //String appointmentEndDT = rs.getString("End");
                String appointmentLocation = rs.getString("Location");
                int appointmentCustomerID = rs.getInt("Customer_ID");
            int appointmentStaffID = rs.getInt("User_ID");
            int appointmentContactID = rs.getInt("Contact_ID");

                Appointment appointment = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType,
                        datePicker, appointmentStartLocalTime, appointmentEndLocalTime, appointmentCustomerID, appointmentStaffID, appointmentContactID);
                lClientTables.add(appointment);
            }
            return lClientTables;
        }

    /** Method that creates an observable list for that can be used to display the count of the different types of appointment.
     * @param connection Needs a connection to a database to function.
     * @return Returns the list of types of appointments and a count of how many there are.
     * @throws SQLException
     */
    public static ObservableList<TypeCount> appointmentTypes(Connection connection) throws SQLException {


        ObservableList<TypeCount> appointmentTypes = FXCollections.observableArrayList();
        String query = "SELECT Type, COUNT(Type) FROM client_schedule.appointments GROUP BY Type;";
        PreparedStatement ps = JDBC.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String type = rs.getString("Type");
            int count = rs.getInt("COUNT(Type)");
            TypeCount tc = new TypeCount(type, count);
            appointmentTypes.add(tc);
        }
        return appointmentTypes;
    }
}

