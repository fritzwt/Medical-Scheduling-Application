package wesfritzc195pa.controllers;


import wesfritzc195pa.SQLMethods.LoginSQL;
import wesfritzc195pa.SQLMethods.LoginSQL.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import wesfritzc195pa.models.Appointment;
import wesfritzc195pa.models.SystemUser;
import wesfritzc195pa.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;


import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** LoginController class that controls the Login Page.
 * Controls the actions behind the login fxml.*/
public class loginController implements Initializable {




    Stage stage;

    Parent scene;


    @FXML
    private Button enterLoginButton;

    @FXML
    private Button exitAppButton;

    @FXML
    private Label locationLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label userLocationLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label enterLoginLabel;

    @FXML
    private TextField userNameTextField;


    /**This method allows the user to enter their login information.
     * Errors are thrown if the fields are blank.
     * If a valid user, sends them to the directory page.
     * Sends an alert to inform the user if they have an appointment within 15 minutes of logging in or not.
     * @param event links to the act of pressing the Enter button.*/
    @FXML
    public void onClickEnterLoginInfo(ActionEvent event) throws IOException, SQLException {
        OffsetDateTime time = OffsetDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        System.out.println(time);


        ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());

        String name = userNameTextField.getText();
        String password = passwordTextField.getText();

        if (name.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, rb.getString("nameBlankError"));
            alert.showAndWait();
        } else if (password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, rb.getString("passwordBlankError"));
            alert.showAndWait();
        } else {

            boolean valid = LoginSQL.validateUser(name, password);
            System.out.println(valid);
            if (valid == true) {
                String succFail = "Successful Login!";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, rb.getString("loginSuccess"));
                alert.showAndWait();
                ObservableList<SystemUser> loginObjectList = FXCollections.observableArrayList();
                SystemUser login = new SystemUser(name, password, time, succFail);
                loginObjectList.add(login);

                // sends user to the directory after verifying login info.
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(Main.class.getResource("directory.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();

                // create obs. list for appointments for times
                ObservableList<Appointment> appointmentTimesList = wesfritzc195pa.SQLMethods.AppointmentSQL.getAllAppointments();

                // make two empty ObservableList <Appointment> .. first one is for appointments 15 mins AFTER LOGIN
                // second one is for appointments 15 mins BEFORE LOGIN
                ObservableList<Appointment> appointmentsInFifteenMinutes = FXCollections.observableArrayList();
                ObservableList<Appointment> appointmentsFifteenAgo = FXCollections.observableArrayList();

                // populates the timestamp lists made for appointments
                for (Appointment apt : appointmentTimesList) {
                    LocalTime timeForAlert = apt.getAppointmentStartDT();
                    LocalDate dateForAlert = apt.getDatePicker();
                    LocalDateTime dateTimeForAlert = LocalDateTime.of(dateForAlert, timeForAlert);

                    if (dateTimeForAlert.isAfter(LocalDateTime.now()) && dateTimeForAlert.isBefore(LocalDateTime.now().plusMinutes(15))) {
                        appointmentsInFifteenMinutes.add(apt);

                    } else if (dateTimeForAlert.isBefore(LocalDateTime.now()) && dateTimeForAlert.isAfter(LocalDateTime.now().minusMinutes(15))) {
                        appointmentsFifteenAgo.add(apt);
                    }
                }

                // Logical check for no appointments...

                if (appointmentsInFifteenMinutes.isEmpty() && appointmentsFifteenAgo.isEmpty()) {
                            Alert noAppointmentsInFifteen = new Alert(Alert.AlertType.INFORMATION, "Welcome! You have no appointments close to your login time.");
                            noAppointmentsInFifteen.showAndWait();
                } else {

                    // what to do if appointments are found... make a list of strings, and then make one alert for that list
                            ObservableList<String> aptscomingup = FXCollections.observableArrayList();
                            for (Appointment appointment : appointmentsInFifteenMinutes) {
                                String aptId = String.valueOf(appointment.getAppointmentID());
                                LocalDate aptDate = appointment.getDatePicker();
                                LocalTime aptStart = appointment.getAppointmentStartDT();
                                LocalDateTime aptDateStart = LocalDateTime.of(aptDate, aptStart);
//                                String starttime = String.valueOf(appointment.getAppointmentStartDT());
                                String aptStarts = "Appointment " + aptId + ", starts at: " + aptDateStart + ".";
                                aptscomingup.add(aptStarts);
                            }
                            for (Appointment appointment: appointmentsFifteenAgo) {
                                String aptId = String.valueOf(appointment.getAppointmentID());
                                String starttime = String.valueOf(appointment.getAppointmentStartDT());
                                String aptPassed = "Appointment " + aptId + " started at " +starttime + ".";
                                aptscomingup.add(aptPassed);
                            }
                            Alert fifteenOut = new Alert(Alert.AlertType.INFORMATION, "Welcome! You have the following appointments that have started or are starting within 15 minutes: " + aptscomingup.toString());
                            fifteenOut.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            fifteenOut.showAndWait();
                        }
                    } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, rb.getString("eitherNotRecognized"));
                alert.showAndWait();
                userNameTextField.clear();
                passwordTextField.clear();
            }
        }
    }




    /** Method that closes the application upon clicking.
     * @param event Fires off when the exit button is clicked.*/
    @FXML
    void onClickExitApp(ActionEvent event) {
        System.exit(0);
    }


    /** The initialize method for the login controller.
     * Populates the various labels, as well as obtains the user's location of their computer.
     * Also displays the labels in English or French, depending on what language they run their computer in.*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("Nat", Locale.getDefault());
        loginLabel.setText(rb.getString("Login"));
        userNameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        locationLabel.setText(rb.getString("Location"));
        enterLoginButton.setText(rb.getString("Submit"));
        exitAppButton.setText(rb.getString("Exit"));

        ZoneId zone = ZoneId.systemDefault();
        userLocationLabel.setText(String.valueOf(zone));




    }
}

