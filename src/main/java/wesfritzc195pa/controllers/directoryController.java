package wesfritzc195pa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import wesfritzc195pa.Main;

import java.io.IOException;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**DirectoryController class that controls the Directory Page.
 * Functions as a hub to access the other parts of the application.*/
public class directoryController implements Initializable{

    Stage stage;

    Parent scene;


    @FXML
    private Button createAppointmentButton;



    @FXML
    private Button exitAppButton;

    @FXML
    private Button modifyAppointmentButton;

    @FXML
    private Button viewCustomerButton;

    @FXML
    private Button viewReportsButton;

    /**Method to go to the appointments page.
     * @param event User taken to appointments page upon clicking appointments button.*/
    @FXML
    public void onClickViewAppointments(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Main.class.getResource("viewAppointment.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**Method to go to the customer page.
     * @param event User taken to customers page upon clicking customers button.*/
    @FXML
    public void onClickViewCustomer(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Main.class.getResource("viewCustomer.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**Method to go to the reports page.
     * @param event User taken to reports page upon clicking reports button.*/
    @FXML
    public void onClickViewReports(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Main.class.getResource("reports.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**Method to exit the application.
     * @param event User closes application upon clicking exit button.*/
    @FXML
    public void onClickExitApp(ActionEvent event) throws IOException{
        System.exit(0);
    }

    /**Method to initialize any possible tables on directory page.
     * Unused currently, but still here in case of future implementation.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
