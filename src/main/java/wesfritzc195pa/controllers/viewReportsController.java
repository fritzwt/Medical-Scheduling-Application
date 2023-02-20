package wesfritzc195pa.controllers;

import wesfritzc195pa.SQLMethods.CustomerSQL;
import wesfritzc195pa.SQLMethods.ReportsSQL;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import wesfritzc195pa.models.Appointment;
import wesfritzc195pa.Main;
import wesfritzc195pa.models.TypeCount;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/** ViewReportsController class that controls the Reports Page.
 * This displays three reports from ReportsSQL that the user can view pertaining to application information.
 * CONTAINS MY SECOND LAMBDA in below method, onActionBackToDirectory().
 * Used by being called to create a confirmation alert asking the user if they want to go back to the directory.
 * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
 * This Lambda handles this type of confirmation alert, and can easily be modified to handle other similar situations.
 * */
public class viewReportsController implements Initializable {

    Stage stage;

    Parent scene;

    @FXML
    private TableView<TypeCount> typeTableView;
    @FXML
    private TableColumn<TypeCount, String> typeTableTypeColumn;
    @FXML
    private TableColumn<TypeCount, Integer> typeTableCountColumn;

    @FXML
    private TableColumn<Appointment, Integer> aAppointmentIDColumn;

    @FXML
    private TableColumn<Appointment, Integer> aCustomerColumn;

    @FXML
    private TableColumn<Appointment, String> aDescriptionColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> aEndColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> aStartColumn;

    @FXML
    private TableColumn<Appointment, String> aTitleColumn;

    @FXML
    private TableColumn<Appointment, String> aTypeColumn;

    @FXML
    private TableView<Appointment> anikaTableView;

    @FXML
    private TableColumn<Appointment, LocalDate> aDateColumn;


    @FXML
    private TableColumn<Appointment, LocalDate> dDateColumn;

    @FXML
    private TableColumn<Appointment, LocalDate> lDateColumn;

    @FXML
    private Label aprLabel;

    @FXML
    private Label augLabel;

    @FXML
    private Label canadaLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<Appointment, Integer> dAppointmentIDColumn;

    @FXML
    private TableColumn<Appointment, Integer> dCustomerColumn;

    @FXML
    private TableColumn<Appointment, String> dDescriptionColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> dEndColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> dStartColumn;

    @FXML
    private TableColumn<Appointment, String> dTitleColumn;

    @FXML
    private TableColumn<Appointment, String> dTypeColumn;

    @FXML
    private TableView<Appointment> danielTableView;

    @FXML
    private Label dbLabel;

    @FXML
    private Label decLabel;

    @FXML
    private Label febLabel;

    @FXML
    private Label janLabel;

    @FXML
    private Label julLabel;

    @FXML
    private Label junLabel;

    @FXML
    private TableColumn<Appointment, Integer> lAppointmentIDColumn;

    @FXML
    private TableColumn<Appointment, Integer> lCustomerColumn;

    @FXML
    private TableColumn<Appointment, String> lDescriptionColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> lEndColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> lStartColumn;

    @FXML
    private TableColumn<Appointment, String> lTitleColumn;

    @FXML
    private TableColumn<Appointment, String> lTypeColumn;

    @FXML
    private TableView<Appointment> liTableView;

    @FXML
    private Label marLabel;

    @FXML
    private Label mayLabel;

    @FXML
    private Label novLabel;

    @FXML
    private Label octLabel;

    @FXML
    private Label otrLabel;

    @FXML
    private Label psLabel;

    @FXML
    private Label sepLabel;

    @FXML
    private Label ukLabel;

    @FXML
    private Label usLabel;

    /**
     * THIS METHOD CONTAINS MY SECOND LAMBDA!
     * Used by being called to create a confirmation alert asking the user if they want to go back to the directory.
     * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
     * This Lambda handles this type of confirmation alert, and can easily be modified to handle other similar situations.
     * Method to take user back to the directory.
     * Contains a confirmation via lambda to confirm this decision.
     * @param event Happens when user clicks the back to directory button.
     * @throws IOException
     */
    @FXML
    public void onActionBackToDirectory(ActionEvent event) throws IOException {

        // **********Uses second LAMBDA!!*****************
        // calls to secondLambdaInterface to set up confirmation alert to return to the directory.
        wesfritzc195pa.toolbox.secondLambdaInterface message = s -> "Back " + s;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message.getMessage("to the directory?"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(Main.class.getResource("directory.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    /**Method that initializes the reports page.
     * Draws data from ReportsSQL to post here.
     * Involves three tables, one for each contact's appointments.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = CustomerSQL.connectToDB();

            // Appointment by type.



            ObservableList <TypeCount> appointmentTypes = ReportsSQL.appointmentTypes(connection);
            //ObservableList appointmentCounts = ReportsSQL.appointmentCountsByType(connection);



            // appointments by month.
            ObservableList janAppointments = ReportsSQL.janFilterAppointments(connection);
            ObservableList febAppointments = ReportsSQL.febFilterAppointments(connection);
            ObservableList marAppointments = ReportsSQL.marFilterAppointments(connection);
            ObservableList aprAppointments = ReportsSQL.aprFilterAppointments(connection);
            ObservableList mayAppointments = ReportsSQL.mayFilterAppointments(connection);
            ObservableList junAppointments = ReportsSQL.junFilterAppointments(connection);
            ObservableList julAppointments = ReportsSQL.julFilterAppointments(connection);
            ObservableList augAppointments = ReportsSQL.augFilterAppointments(connection);
            ObservableList sepAppointments = ReportsSQL.sepFilterAppointments(connection);
            ObservableList octAppointments = ReportsSQL.octFilterAppointments(connection);
            ObservableList novAppointments = ReportsSQL.novFilterAppointments(connection);
            ObservableList decAppointments = ReportsSQL.decFilterAppointments(connection);

            // number of appointments per month.
            String a = String.valueOf(janAppointments.get(0));
            String b = String.valueOf(febAppointments.get(0));
            String c = String.valueOf(marAppointments.get(0));
            String d = String.valueOf(aprAppointments.get(0));
            String e = String.valueOf(mayAppointments.get(0));
            String f = String.valueOf(junAppointments.get(0));
            String g = String.valueOf(julAppointments.get(0));
            String h = String.valueOf(augAppointments.get(0));
            String i = String.valueOf(sepAppointments.get(0));
            String j = String.valueOf(octAppointments.get(0));
            String k = String.valueOf(novAppointments.get(0));
            String l = String.valueOf(decAppointments.get(0));

            //sets text for each month's amount of appointments.
            janLabel.setText(a);
            febLabel.setText(b);
            marLabel.setText(c);
            aprLabel.setText(d);
            mayLabel.setText(e);
            junLabel.setText(f);
            julLabel.setText(g);
            augLabel.setText(h);
            sepLabel.setText(i);
            octLabel.setText(j);
            novLabel.setText(k);
            decLabel.setText(l);

            //list of appointments per type.
            ObservableList pAppointments = ReportsSQL.numberPAppointments(connection);
            ObservableList dbAppointments = ReportsSQL.numberDBAppointments(connection);
            ObservableList otrAppointments = ReportsSQL.numberOTRAppointments(connection);

            //retrieves number of appointments per type.
            String p = String.valueOf(pAppointments.get(0));
            String db = String.valueOf(dbAppointments.get(0));
            String otr = String.valueOf(otrAppointments.get(0));

            //sets labels for number of each type of appointment.
//            psLabel.setText(p);
//            dbLabel.setText(db);
//            otrLabel.setText(otr);

            //makes list of customers per country.
            ObservableList usCustomersList = ReportsSQL.numberUSCustomers(connection);
            ObservableList ukCustomersList = ReportsSQL.numberUKCustomers(connection);
            ObservableList canadaCustomersList = ReportsSQL.numberCanadaCustomers(connection);

            //retrieves number of customers per country.
            String us = String.valueOf(usCustomersList.get(0));
            String uk = String.valueOf(ukCustomersList.get(0));
            String can = String.valueOf(canadaCustomersList.get(0));

            //sets labels for number of customers per country.
            usLabel.setText(us);
            ukLabel.setText(uk);
            canadaLabel.setText(can);

            //makes a list of appointments for each client pertaining to their tables.
            ObservableList<Appointment> aClientTables = ReportsSQL.fillAnikaTable(connection);
            ObservableList<Appointment> dClientTables = ReportsSQL.fillDanielTable(connection);
            ObservableList<Appointment> lClientTables = ReportsSQL.fillLiTable(connection);

            //sets the appointments into their individual tables.
            anikaTableView.setItems(aClientTables);
            danielTableView.setItems(dClientTables);
            liTableView.setItems(lClientTables);

            //fills each contact's tables with their columns, along with their information.
            aAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
            dAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
            lAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
            aTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentTitle"));
            dTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentTitle"));
            lTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentTitle"));
            aDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentDescription"));
            dDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentDescription"));
            lDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentDescription"));
            aTypeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentType"));
            dTypeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentType"));
            lTypeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentType"));
            aStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentStartDT"));
            dStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentStartDT"));
            lStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentStartDT"));
            aEndColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentEndDT"));
            dEndColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentEndDT"));
            lEndColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("appointmentEndDT"));
            aCustomerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentCustomerID"));
            dCustomerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentCustomerID"));
            lCustomerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentCustomerID"));
            aDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("datePicker"));
            dDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("datePicker"));
            lDateColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("datePicker"));

            typeTableView.setItems(appointmentTypes);
            typeTableTypeColumn.setCellValueFactory(new PropertyValueFactory<TypeCount, String>("type"));
            typeTableCountColumn.setCellValueFactory(new PropertyValueFactory<TypeCount, Integer>("count"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}