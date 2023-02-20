package wesfritzc195pa;

import wesfritzc195pa.SQLMethods.CustomerSQL;
import wesfritzc195pa.helper.JDBC;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;

/**This is the Main Class for my Software II project.
 * Contains the primary launchpad for my application.*/
public class Main extends Application {

    // makes fully qualified name to call resource bundle
   // private static final String FILENAME = "resources/Nat";

    /**This method fetches the login page.
     * Loads the login FXML.
     * @param stage sets a stage for the FXML to load to.*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Wes Fritz C195 Project");
        stage.setScene(scene);
        stage.show();
        }

    /**This is the main method for the main class.
     * Opens the connection to the database, launches the application, then closes the connection when done.
     * @param args launches with contained arguments.*/
    public static void main(String[] args) throws SQLException {

        // database connection opened
        JDBC.openConnection();

        Connection connection = CustomerSQL.connectToDB();
        CustomerSQL.selectToFillCustomerTable(connection);

        // launches application to Login screen
        launch(args);


        // database connection closed
        JDBC.closeConnection();


        }
    }

