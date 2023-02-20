package wesfritzc195pa.controllers;

import wesfritzc195pa.SQLMethods.AppointmentSQL;
import wesfritzc195pa.SQLMethods.ContactsSQL;
import wesfritzc195pa.SQLMethods.CustomerSQL;
import wesfritzc195pa.SQLMethods.LoginSQL;
import wesfritzc195pa.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import wesfritzc195pa.models.Appointment;
import wesfritzc195pa.models.Contact;
import wesfritzc195pa.toolbox.TimeConversions;
import wesfritzc195pa.toolbox.firstLambdaInterface;
import wesfritzc195pa.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.time.LocalDateTime.of;

/**
 * ViewAppointmentController class that controls the Appointment Page.
 * Contains the ability to add/modify appointments, and a viewing table.
 * CONTAINS MY FIRST LAMBDA INSIDE BELOW METHOD onClickSaveAppointment().
 * Used by being called to create an informational alert informing the user of a successful customer/appointment add/update.
 * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
 * This Lambda handles this type of informational alert, and can easily be modified to handle other similar situations.
 * CONTAINS MY SECOND LAMBDA INSIDE BELOW METHOD onActionCancel().
 * Used by being called to create a confirmation alert asking the user if they want to go back to the directory.
 * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
 * This Lambda handles this type of confirmation alert, and can easily be modified to handle other similar situations.
 * */

public class viewAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton addAppointmentRadioButton;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIDTableColumn;

    @FXML
    private TextField appointmentIDTextField;

    @FXML
    private TextField userIDDisabledTextField;

    @FXML
    private ComboBox<Integer> customerComboBox;

    @FXML
    private ComboBox<Integer> userIDComboBox;

    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<Appointment, String> contactTableColumn;

    @FXML
    private TextField contactTextField;

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private TableColumn<Appointment, Integer> customerIDTableColumn;

    @FXML
    private TextField customerIDTextField;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private TableColumn<Appointment, String> descriptionTableColumn;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TableColumn<Appointment, LocalTime> endTableColumn;

    @FXML
    private DatePicker datePicker;


    @FXML
    private ComboBox<String> endComboBox;

    @FXML
    private ComboBox<String> startComboBox;

    @FXML
    private TableColumn<Appointment, String> locationTableColumn;

    @FXML
    private TextField locationTextField;

    @FXML
    private RadioButton modifyAppointmentRadioButton;

    @FXML
    private RadioButton monthlyAppointmentViewRadioButton;

    @FXML
    private Button saveAppointmentButton;

    @FXML
    private TableColumn<Appointment, LocalTime> startTableColumn;

//    @FXML
//    private TextField startTextField;

    @FXML
    private TableColumn<Appointment, String> titleTableColumn;

    @FXML
    private TextField titleTextField;

    @FXML
    private TableColumn<Appointment, String> typeTableColumn;

    @FXML
    private TextField typeTextField;

    @FXML
    private TableColumn<Appointment, Integer> userIDTableColumn;

    @FXML
    private TextField userIDTextField;

    @FXML
    private RadioButton weelkyAppointmentViewRadioButton;

    @FXML
    private RadioButton allAppointmentsRadioButton;

    @FXML
    private TableColumn dateTableColumn;


    /**
     * THIS METHOD CONTAINS MY SECOND LAMBDA!
     * Used by being called to create a confirmation alert asking the user if they want to go back to the directory.
     * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
     * This Lambda handles this type of confirmation alert, and can easily be modified to handle other similar situations.
     * This method below is used to exit the appointment page and go back to the directory.
     * Also displays an alert to warn the user of unsaved changes.
     * The unsaved changes alert is performed by my second Lambda.
     *
     * @param event happens when user presses the cancel button.
     * @throws IOException
     */

    @FXML
    public void onActionCancel(ActionEvent event) throws IOException {

        // **********Uses second LAMBDA!!*****************
        // calls to secondLambdaInterface to set up confirmation alert to return to the directory.
        wesfritzc195pa.toolbox.secondLambdaInterface message = s -> "Changes " + s;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message.getMessage("will not be saved. Are you sure?"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(Main.class.getResource("directory.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    /**
     * Method to delete the selected appointment.
     * Displays an alert to both confirm and affirm the deletion of an appointment.
     * Also clears text fields upon deletion.
     *
     * @param event happens when user clicks the delete button.
     */
    @FXML
    public void onActionDeleteAppointment(ActionEvent event) throws IOException, SQLException {
        if (!modifyAppointmentRadioButton.isSelected()) {
            Alert wrongMode = new Alert(Alert.AlertType.ERROR, "Please select Modify Appointment", ButtonType.OK);
            wrongMode.showAndWait();
        } else {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "This will Delete the selected Appointment, are you sure?", ButtonType.YES, ButtonType.CANCEL);
            confirm.showAndWait();
            ButtonBar.ButtonData x = confirm.getResult().getButtonData();
            if (!x.isCancelButton()) {
                ObservableList<Appointment> appointmentToDelete = FXCollections.observableArrayList();
                appointmentToDelete.add(appointmentsTableView.getSelectionModel().getSelectedItem());
                System.out.println(appointmentToDelete);
                Connection connection = JDBC.openConnection();
                AppointmentSQL.deleteAppointment(connection, appointmentToDelete);
                appointmentsTableView.setItems(AppointmentSQL.getAllAppointments());
                clearTextFields();
                Alert deleteSuccess = new Alert(Alert.AlertType.INFORMATION, "Appointment deleted. Appointment ID: " +
                        appointmentToDelete.get(0).getAppointmentID() + ". Type: " + appointmentToDelete.get(0).getAppointmentType() +
                        ".", ButtonType.OK);
                deleteSuccess.showAndWait();

            } else {
                appointmentsTableView.getSelectionModel().clearSelection();
                clearTextFields();
                resetAddModify();

            }
        }
    }

    /**
     * THIS METHOD CONTAINS MY FIRST LAMBDA!
     * Used by being called to create an informational alert informing the user of a successful customer/appointment add/update.
     * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
     * This Lambda handles this type of informational alert, and can easily be modified to handle other similar situations.
     * This method below saves the created or modified appointment.
     * Displays an alert to confirm and affirm the addition/modification of an appointment.
     * Also clears text fields upon doing so.
     * @param event Happens when user clicks Save.
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void onActionSaveAppointment(ActionEvent event) throws IOException, SQLException {
        try {
            if (!addAppointmentRadioButton.isSelected() && !modifyAppointmentRadioButton.isSelected()) {
                Alert selectMode = new Alert(Alert.AlertType.ERROR, "Choose Add or Modify Appointment");
                selectMode.showAndWait();
            } else if (addAppointmentRadioButton.isSelected() && !modifyAppointmentRadioButton.isSelected()) {
                //Gather Info from the Fields/Boxes etc...
                String title = titleTextField.getText();
                String description = descriptionTextField.getText();
                String location = locationTextField.getText();
                String type = typeTextField.getText();
                LocalDate date = datePicker.getValue();
                LocalTime start = LocalTime.parse(startComboBox.getSelectionModel().getSelectedItem());
                LocalTime end = LocalTime.parse(endComboBox.getSelectionModel().getSelectedItem());
                String customerID = String.valueOf(customerComboBox.getSelectionModel().getSelectedItem());
                String UserID = String.valueOf(userIDComboBox.getSelectionModel().getSelectedItem());
                String contactName = contactComboBox.getSelectionModel().getSelectedItem();
                int contactID = ContactsSQL.getIDbyLookupContactName(contactName);

//  Begin Logical Checks on Appointment Times and Conflicts


                ObservableList<Appointment> allAppointments = AppointmentSQL.getAllAppointments();
                LocalDateTime startLDTCheck = of(date, start);
                int s = startLDTCheck.getDayOfWeek().getValue();
                if ((s == 6) || (s == 7)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment cannot be on the weekend. Please pick a different day.");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();

                    return;
                }
                LocalDateTime endLDTCheck = of(date, end);
                if (endLDTCheck.isBefore(startLDTCheck) || endLDTCheck.equals(startLDTCheck)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment End Time cannot be before or at Appointment Start Time.");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();

                    return;
                } else {

                }


                for (Appointment apt : allAppointments) {
                    int aptID = apt.getAppointmentID();
                    LocalDate aptDate = apt.getDatePicker();
                    LocalTime aptStart = apt.getAppointmentStartDT();
                    LocalTime aptEnd = apt.getAppointmentEndDT();
                    LocalDateTime existAptLDTStart = of(aptDate, aptStart);
                    LocalDateTime existAptLDTEnd = of(aptDate, aptEnd);

                    if (startLDTCheck.isAfter(existAptLDTStart) && startLDTCheck.isBefore(existAptLDTEnd) ||
                            startLDTCheck.isEqual(existAptLDTStart) || startLDTCheck.isEqual(existAptLDTEnd)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Requested Appointment Start overlaps Appointment "
                                + aptID + " . Please select a new start time.");
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.showAndWait();

                        return;
                    } else if (endLDTCheck.isAfter(existAptLDTStart) && endLDTCheck.isBefore(existAptLDTEnd) ||
                            endLDTCheck.isEqual(existAptLDTEnd) || endLDTCheck.isEqual(existAptLDTStart)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Requested Appointment End overlaps Appointment "
                                + aptID + " . Please select a new end time.");
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.showAndWait();

                        return;
                    } else if ((startLDTCheck.isAfter(existAptLDTStart) || startLDTCheck.isEqual(existAptLDTStart)) &&
                            (endLDTCheck.isBefore(existAptLDTEnd) || endLDTCheck.isEqual(existAptLDTEnd))) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Requested appointment times are inside of " +
                                "Appointment " + aptID + " . Please select new times for this appointment.");
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.showAndWait();
                        return;
                    } else if (startLDTCheck.isBefore(existAptLDTStart) && endLDTCheck.isAfter(existAptLDTEnd)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Requested Appointment Times encapsulate that" +
                                " of existing Appointment " + aptID + " . Please select revised appointment times" +
                                " that do not conflict.");
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.showAndWait();
                        return;
                    } else {
                    }

                }
                //Prep the Time/Date Info for recording
                Timestamp sqlTimeStart = TimeConversions.timestampUTC(date, start);
                Timestamp sqlTimeEnd = TimeConversions.timestampUTC(date, end);

                // Call the SQL class to add the new appointment to the SQL DB
                AppointmentSQL.addAppointment(title, description, location, contactID, type, date, sqlTimeStart, sqlTimeEnd, customerID, UserID);

                //Refresh the tableview...
                appointmentsTableView.setItems(AppointmentSQL.getAllAppointments());


                // *************FIRST LAMBDA USED!************
                // calls to firstLambdaInterface to set up an Appointment addition information alert.
                firstLambdaInterface information = q -> "Appointment " + q;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, information.displayNewInformation("successfully added!"));
                alert.showAndWait();
                clearTextFields();
                resetComboBoxes();
                resetAddModify();


                // If Modify Appointment is Selected, Save Button will run this section
            } else if (!addAppointmentRadioButton.isSelected() && modifyAppointmentRadioButton.isSelected()) {
                //Gather info from the boxes...
                // First, force a check on if there was a valid appointment selected once again...
                if (appointmentIDTextField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "You must first select an appointment.");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                    return;
                }
                int appointmentID = Integer.parseInt(appointmentIDTextField.getText());

                String title = titleTextField.getText();
                String description = descriptionTextField.getText();
                String location = locationTextField.getText();
                String type = typeTextField.getText();
                LocalDate date = datePicker.getValue();
                LocalTime start = LocalTime.parse(startComboBox.getSelectionModel().getSelectedItem());
                LocalTime end = LocalTime.parse(endComboBox.getSelectionModel().getSelectedItem());
                String customerID = String.valueOf(customerComboBox.getSelectionModel().getSelectedItem());
                int userID = userIDComboBox.getSelectionModel().getSelectedItem();
                String contactName = contactComboBox.getSelectionModel().getSelectedItem();
                int contactID = ContactsSQL.getIDbyLookupContactName(contactName);

                // Call for validation on if the selected time is within buisness hours...
                if (TimeConversions.validateAppointmentStartEndOK(date, start, end) == false) {
                    return;
                } else {


//  Begin Logical Check on Existing Appointment Overlaps

                    ObservableList<Appointment> allAppointments = AppointmentSQL.getAllAppointments();
                    LocalDateTime startLDTCheck = of(date, start);
                    LocalDateTime endLDTCheck = of(date, end);
                    int s = startLDTCheck.getDayOfWeek().getValue();
                    if ((s == 6) || (s == 7)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment cannot be on the weekend. Please pick a different day.");
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.showAndWait();

                        return;
                    }
                    if (endLDTCheck.isBefore(startLDTCheck) || endLDTCheck.equals(startLDTCheck)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment End Time cannot be before or at Appointment Start Time.");
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.showAndWait();
                        return;
                    } else {

                    }
                    int stop = 0;
                    for (Appointment apt : allAppointments) {
                        int aptID = apt.getAppointmentID();
                        LocalDate aptDate = apt.getDatePicker();
                        LocalTime aptStart = apt.getAppointmentStartDT();
                        LocalTime aptEnd = apt.getAppointmentEndDT();
                        LocalDateTime existAptLDTStart = of(aptDate, aptStart);
                        LocalDateTime existAptLDTEnd = of(aptDate, aptEnd);

                        if (startLDTCheck.isAfter(existAptLDTStart) && startLDTCheck.isBefore(existAptLDTEnd) ||
                                startLDTCheck.isEqual(existAptLDTStart) || startLDTCheck.isEqual(existAptLDTEnd)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Requested Appointment Start overlaps Appointment "
                                    + aptID + " . Please select a new start time.");
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.showAndWait();
                            return;
                        } else if (endLDTCheck.isAfter(existAptLDTStart) && endLDTCheck.isBefore(existAptLDTEnd) ||
                                endLDTCheck.isEqual(existAptLDTEnd) || endLDTCheck.isEqual(existAptLDTStart)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Requested Appointment End overlaps Appointment "
                                    + aptID + " . Please select a new end time.");
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.showAndWait();
                            return;
                        } else if ((startLDTCheck.isAfter(existAptLDTStart) || startLDTCheck.isEqual(existAptLDTStart)) &&
                                (endLDTCheck.isBefore(existAptLDTEnd) || endLDTCheck.isEqual(existAptLDTEnd))) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Requested appointment times are inside of " +
                                    "Appointment " + aptID + " . Please select new times for this appointment.");
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.showAndWait();
                            return;
                        } else if (startLDTCheck.isBefore(existAptLDTStart) && endLDTCheck.isAfter(existAptLDTEnd)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Requested Appointment Times encapsulate that" +
                                    " of existing Appointment " + aptID + " . Please select revised appointment times" +
                                    " that do not conflict.");
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.showAndWait();
                            return;
                        } else {
                        }
                    }
                    if (stop != 0) {
                        return;
                    } else {

                        //Prep the time items for SQL Use...
                        Timestamp sqlTimeStart = TimeConversions.timestampUTC(date, start);
                        Timestamp sqlTimeEnd = TimeConversions.timestampUTC(date, end);


                        //Call the SQL function to update the existing appointment in SQL database
                        AppointmentSQL.updateAppointment(appointmentID, title, description, location, type, date, sqlTimeStart, sqlTimeEnd, customerID, userID, contactID);
                        appointmentsTableView.setItems(AppointmentSQL.getAllAppointments());
                        firstLambdaInterface information = q -> "Appointment " + q;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, information.displayNewInformation("successfully modified!"));
                        alert.showAndWait();
                        clearTextFields();
                        resetAddModify();
                        resetComboBoxes();
                        appointmentsTableView.getSelectionModel().clearSelection();
                    }
                }

            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "General SQL Error, Check fields for Special Characters and try again");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            e.printStackTrace();
        }
    }
        /**Method that manages the form if it is in add appointment mode.
         * applies to the radio buttons at the top of the appointment page.
         * @param event Happens when the add/modify radio buttons are toggled.*/
        @FXML
        public void onSelectionAddAppointment (ActionEvent event){
            if (modifyAppointmentRadioButton.isSelected()) {
                modifyAppointmentRadioButton.setSelected(false);
                clearTextFields();
                resetComboBoxes();
                datePicker.setValue(LocalDate.now());
                datePicker.setDayCellFactory(picker -> new DateCell() {

                    /**Method that updates the date picker to display the current date.*/
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = LocalDate.now();

                        setDisable(empty || date.compareTo(today) < 0);
                    }
                });

            } else {

            }
        }

        /** Method to clear comboBoxes.*/
        private void resetComboBoxes () {
            contactComboBox.getSelectionModel().clearSelection();
            startComboBox.getSelectionModel().clearSelection();
            endComboBox.getSelectionModel().clearSelection();
        }

        /** Method to reset the radio buttons after use.*/
        private void resetAddModify () {
            addAppointmentRadioButton.setSelected(false);
            modifyAppointmentRadioButton.setSelected(false);
        }

        /**Method to clear the text fields when called.*/
        private void clearTextFields () {
            appointmentIDTextField.clear();
            titleTextField.clear();
            descriptionTextField.clear();
            locationTextField.clear();
            contactComboBox.setValue("");
            typeTextField.clear();
            datePicker.setValue(LocalDate.now());
            startComboBox.setValue("");
            endComboBox.setValue("");
//            customerComboBox.setValue(Integer.valueOf(""));
//            userIDComboBox.setValue(Integer.valueOf(""));

        }

        /**Method that manages the form if it is in modify appointment mode.
         * applies to the radio buttons at the top of the appointment page.
         * @param event Happens when the add/modify radio buttons are toggled.*/
        @FXML
        public void onSelectionModifyAppointment (ActionEvent event) throws IOException, SQLException {
            if (appointmentsTableView.getSelectionModel().isEmpty()) {
                Alert selection = new Alert(Alert.AlertType.ERROR, "You must select an appointment to modify first.", ButtonType.OK);
                selection.showAndWait();
                modifyAppointmentRadioButton.setSelected(false);
            } else {
                addAppointmentRadioButton.setSelected(false);
                Appointment appointment = appointmentsTableView.getSelectionModel().getSelectedItem();

                // Leave this in, in case future people would like to not be able to modify past appointments.
//                if (appointment.getDatePicker().isBefore(LocalDate.now())) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to modify a past appointment. Select a future appointment, or add a new one.", ButtonType.OK);
//                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//                    alert.showAndWait();
//                } else {
                    appointmentIDTextField.setText(String.valueOf(appointment.getAppointmentID()));
                    titleTextField.setText(appointment.getAppointmentTitle());
                    descriptionTextField.setText(appointment.getAppointmentDescription());
                    locationTextField.setText(appointment.getAppointmentLocation());
                    contactComboBox.getSelectionModel().select(appointment.getAppointmentContactID());
                    typeTextField.setText(appointment.getAppointmentType());
                    datePicker.setValue(appointment.getDatePicker());
                    datePicker.setDayCellFactory(picker -> new DateCell() {

                        /**Method that updates the date picker to display the current date.*/
                        public void updateItem(LocalDate date, boolean empty) {
                            super.updateItem(date, empty);
                            LocalDate today = LocalDate.now();

                            setDisable(empty || date.compareTo(today) < 0);
                        }
                    });
                    startComboBox.getSelectionModel().select(String.valueOf(appointment.getAppointmentStartDT()));
                    endComboBox.getSelectionModel().select(String.valueOf(appointment.getAppointmentEndDT()));
                    customerComboBox.setValue(appointment.getAppointmentCustomerID());
                    userIDComboBox.setValue(appointment.getAppointmentStaffID());
                    appointmentsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != oldSelection) {
                        clearTextFields();
                        resetComboBoxes();
                        resetAddModify();
                    } else {
                    }
                });
                }
            }


        /**Method that changes the table to view all appointments.
         * @param event happens when user clicks the radio button for all appointments.*/
        @FXML
        public void onSelectionAllAppointmentsView (ActionEvent event) throws IOException, SQLException {
            weelkyAppointmentViewRadioButton.setSelected(false);
            modifyAppointmentRadioButton.setSelected(false);
            appointmentsTableView.setItems(AppointmentSQL.getAllAppointments());
        }

        /**Method that changes the table to view appointments by month.
         * @param event happens when user clicks the radio button for monthly appointments.*/
        @FXML
        public void onSelectionMonthlyView (ActionEvent event) throws IOException {
            allAppointmentsRadioButton.setSelected(false);
            weelkyAppointmentViewRadioButton.setSelected(false);
            try {
                ObservableList<Appointment> allAppointmentsList = AppointmentSQL.getAllAppointments();
                ObservableList<Appointment> appointmentsMonth = FXCollections.observableArrayList();

                LocalDate currentMonthStart = LocalDate.now().minusMonths(1);
                LocalDate currentMonthEnd = LocalDate.now().plusMonths(1);


                if (allAppointmentsList != null) {
                    allAppointmentsList.forEach(appointment -> {
                        if (appointment.getDatePicker().isAfter(currentMonthStart) && appointment.getDatePicker().isBefore(currentMonthEnd)) {
                            appointmentsMonth.add(appointment);
                        }
                        appointmentsTableView.setItems(appointmentsMonth);
                    });
                }
            } catch (SQLException e) {
                JDBC.sqlAlert();
                e.printStackTrace();
            }
        }

        /**An unused method to select a contact.
         * May be utilized in the future, for added functionality
         * Was replaced by contactComboxFill() down below.*/
        @FXML
        public void onSelectionPickContact (ActionEvent event) throws IOException {

        }

        /**Method that changes the table to view appointments by week.
         * @param event happens when user clicks the radio button for weekly appointments.*/
        @FXML
        public void onSelectionWeeklyView (ActionEvent event) throws IOException {
            monthlyAppointmentViewRadioButton.setSelected(false);
            allAppointmentsRadioButton.setSelected(false);
            try {
                ObservableList<Appointment> allAppointmentsList = AppointmentSQL.getAllAppointments();
                ObservableList<Appointment> appointmentsWeek = FXCollections.observableArrayList();

                LocalDate currentWeekStart = LocalDate.now().minusWeeks(1);
                LocalDate currentWeekEnd = LocalDate.now().plusWeeks(1);

                if (allAppointmentsList != null)
                    //IDE converted to forEach
                    allAppointmentsList.forEach(appointment -> {
                        if (appointment.getDatePicker().isAfter(currentWeekStart) && appointment.getDatePicker().isBefore(currentWeekEnd)) {
                            appointmentsWeek.add(appointment);
                        }
                        appointmentsTableView.setItems(appointmentsWeek);
                    });
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "General SQL Error, Check fields for Special Characters and try again");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
                e.printStackTrace();
            }
        }

    /** a blank method to help pick a customer for a combo box on the appoinments page.
     * @param event Happens when user clicks the customer combo box.
     * @throws IOException
     */
    @FXML
    public void onSelectionSelectCustomer (ActionEvent event) throws IOException {

        }

/**A method that uses a combobox to pick a contact for an appointment.
 * Uses an observable list of contacts to do so.
 * @return allContacts For use in the combo box.*/
        public ObservableList<Contact> contactComboxFill() throws SQLException {
            ObservableList<Contact> allContacts = ContactsSQL.allContacts();
            return allContacts;
        }

    /**A blank method to help pick a user for a combo box on the appointments page.
     * @param event Happens when user clicks the user ID combo box.
     * @throws SQLException
     */
    @FXML
    public void onSelectionPickUser(ActionEvent event) throws SQLException {

        }

    /** Method creates an observable list to return user IDs from the database.
     * Used in a combo box on the appointments page.
     * @return Return a list of User IDs for use in the combo box.
     * @throws SQLException
     */
    public ObservableList<Integer> UserIDBoxFill() throws SQLException {
        ObservableList<Integer> ID = LoginSQL.getUserIDs();
        return ID;
        }

        /**The initialize method that populates the appointment page's fields.
         * Sets the business hours as well.
         * Sets up the appointments table.*/
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            try {
                ObservableList<Appointment> allAppointments = AppointmentSQL.getAllAppointments();
                ObservableList<Contact> allContacts = contactComboxFill();
                ObservableList<String> contactNames = Contact.allContactsByName(allContacts);
                ObservableList<String> appointmentStartTimes = FXCollections.observableArrayList();
                Connection connection = JDBC.openConnection();
                ObservableList<Integer> allCustomers = CustomerSQL.getCustomersByID();

                LocalTime businessStart = LocalTime.from(ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.systemDefault()));
                System.out.println(businessStart);
                LocalTime businessEnd = LocalTime.from(ZonedDateTime.of(LocalDate.now(), LocalTime.of(22, 0), ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.systemDefault()));
                System.out.println(businessEnd);
                LocalTime firstAppointment = businessStart.atOffset(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())).toLocalTime();
                LocalTime lastAppointment = businessEnd.atOffset(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())).toLocalTime();
                if (!firstAppointment.equals(0) || !lastAppointment.equals(0)) {
                    while (firstAppointment.isBefore(lastAppointment)) {

                        appointmentStartTimes.add(String.valueOf(firstAppointment));
                        firstAppointment = firstAppointment.plusMinutes(15);
                    }
                }
                startComboBox.setItems(appointmentStartTimes);
                endComboBox.setItems(appointmentStartTimes);
                customerComboBox.setItems(allCustomers);
                userIDComboBox.setItems(LoginSQL.getUserIDs());

                appointmentIDTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
                titleTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentTitle"));
                descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentDescription"));
                locationTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentLocation"));
                contactTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentContactID"));
                typeTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentType"));
                dateTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("datePicker"));
                startTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentStartDT"));
                endTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentEndDT"));
                customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentCustomerID"));
                userIDTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentStaffID"));
                allAppointmentsRadioButton.setSelected(true);
                appointmentsTableView.setItems(allAppointments);
                contactComboBox.setItems(contactNames);


            } catch (SQLException e) {
                JDBC.sqlAlert();
                e.printStackTrace();
            }
        }
    }


