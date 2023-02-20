package wesfritzc195pa.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;

/**Class that connects the java application to the database.
 * I used a local database for my project, via IntelliJ and mySQL.*/
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";

    //localhost below is where our local database is hosted.
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";

    // trying a new connection string....
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?useLegacyDatetimeCode=false&serverTimezone=UTC";

//    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference

    // holds login information for the MySQL database.
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /**Method that opens the connection to the database.
     * Gives a line of output to signify successful connection.
     * @return connection -- gives connection to the database when fired.*/
    public static Connection openConnection()
    {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println(rb.getString("connectionSuccessful"));

        } catch(Exception e) {
            ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());
            System.out.println(rb.getString("Error") + " " + e.getMessage());
        } return connection;
    }

    /**Method that closes the connection to the database.
     * Gives a line of output to signify successful disconnection.*/
    public static void closeConnection() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());
            connection.close();
            System.out.println(rb.getString("connectionClosed"));
        }
        catch(Exception e)
        {
            ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());
            System.out.println(rb.getString("Error") + " " + e.getMessage());
        }
    }

    /**Method to signify an error within SQL.
     * Helpful when trying to make SQL queries that need adjustment.*/
    public static void sqlAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "There is a general SQL Error, refer to terminal output", ButtonType.OK);
        alert.showAndWait();
    }

}
