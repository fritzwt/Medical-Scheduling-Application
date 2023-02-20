package wesfritzc195pa.controllers;

import javafx.scene.layout.Region;
import wesfritzc195pa.SQLMethods.AppointmentSQL;
import wesfritzc195pa.SQLMethods.CustomerSQL;
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
import javafx.stage.Stage;
import wesfritzc195pa.models.Country;
import wesfritzc195pa.models.Customer;
import wesfritzc195pa.models.StateProvince;
import wesfritzc195pa.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** ViewCustomerController class that controls the Customer Page.
 * Allows the viewCustomer FXML to behave properly for the addition/modification of customers.
 * CONTAINS MY FIRST LAMBDA INSIDE BELOW METHOD onClickSaveCustomer().
 * Used by being called to create an informational alert informing the user of a successful customer/appointment add/update.
 * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
 * This Lambda handles this type of informational alert, and can easily be modified to handle other similar situations.
 * CONTAINS MY SECOND LAMBDA INSIDE BELOW METHOD onClickCancel().
 * Used by being called to create a confirmation alert asking the user if they want to go back to the directory.
 * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
 * This Lambda handles this type of confirmation alert, and can easily be modified to handle other similar situations.
 * */
public class viewCustomerController implements Initializable {

    Stage stage;

    Parent scene;

    @FXML
    private RadioButton addCustomerRadioButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private TableColumn<Customer, String> customerAddressColumn;

    @FXML
    private TextField customerAddressTextField;

    @FXML
    private TableColumn<Customer, String> customerCountryColumn;

    @FXML
    private ComboBox<Country> customerCountryComboBox;

    @FXML
    private TableColumn<Customer, Integer> customerIDColumn;

    @FXML
    private TextField customerIDTextField;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TextField customerNameTextField;

    @FXML
    private TableColumn<Customer, String> customerPhoneColumn;

    @FXML
    private TextField customerPhoneNumberTextField;

    @FXML
    private TableColumn<Customer, String> customerPostalCodeColumn;

    @FXML
    private TextField customerPostalCodeTextField;

    @FXML
    private TableColumn<Customer, String> customerStateProvinceColumn;

    @FXML
    private ComboBox<StateProvince> customerStateProvinceComboBox;

    @FXML
    private ToggleGroup customerToggleGroup;

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private RadioButton modifyCustomerRadioButton;

    @FXML
    private Button saveCustomerButton;

    /**The method to initialize all fields on the customers page.
     * Fills the table with data as well for the user to view/interact with.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = CustomerSQL.connectToDB();
            ObservableList<Customer> CustomerInfo = CustomerSQL.selectToFillCustomerTable(connection);
            ObservableList<Country> CountryInfo = CustomerSQL.selectToFillCountriesComboBox(connection);
            ObservableList<StateProvince> StateProvinceInfo = CustomerSQL.selectToFillStateProvinceComboBox(connection);

            // customersTable.setItems(/*Something in here to grab the "class with a get statement" from SQL"*/);

            customerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
            customerAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
            customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPostalCode"));
            customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
            customerStateProvinceColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerStateProvince"));
            customerCountryColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerCountry"));

            customersTable.setItems(CustomerInfo);
            customerCountryComboBox.setItems(CountryInfo);
            //customerStateProvinceComboBox.setItems(StateProvinceInfo);
//        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * THIS METHOD CONTAINS MY SECOND LAMBDA!
     * Used by being called to create a confirmation alert asking the user if they want to go back to the directory.
     * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
     * This Lambda handles this type of confirmation alert, and can easily be modified to handle other similar situations.
     * A method to send the user back to the directory.
     * Sends an alert via lambda for the customer to affirm this action.
     * @param event happens when the cancel button is clicked.
     * @throws IOException
     */
    @FXML
    public void onClickCancel(ActionEvent event) throws IOException {

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
     * THIS METHOD CONTAINS MY FIRST LAMBDA!
     * Used by being called to create an informational alert informing the user of a successful customer/appointment add/update.
     * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
     * This Lambda handles this type of informational alert, and can easily be modified to handle other similar situations.
     * Method used to save or modify a customer.
     * Contains alerts and conditions to guide the saving of customer data.
     * Swaps between saving and modifying based upon which radio button is selected.
     * @param event happens when the save button is clicked and customer information is in text fields.
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void onClickSaveCustomer(ActionEvent event) throws IOException, SQLException {
        Alert emptyField = new Alert(Alert.AlertType.ERROR, "There is an Empty Field in Customer Info, please correct!", ButtonType.OK);

    // Check to see if this is customer has an ID number or not before continuing...
        boolean addCustomer = false;
        if (customerIDTextField.getText().isEmpty()) {
            addCustomer = true;
        } else {
            addCustomer = false;
        }

        try {
            Connection connection = CustomerSQL.connectToDB();
            // Logic for making sure the fields are set:
            String updatedName = null;
            if (!customerNameTextField.getText().isBlank()) {
                updatedName = customerNameTextField.getText();
            } else {
                emptyField.showAndWait();
            }
            String updatedAddress = null;
            if (!customerAddressTextField.getText().isBlank()) {
               updatedAddress = customerAddressTextField.getText();
            } else {
                emptyField.showAndWait();
            }
            String updatedPhone = null;
            if (!customerPhoneNumberTextField.getText().isBlank()) {
                updatedPhone = customerPhoneNumberTextField.getText();
            } else {
                emptyField.showAndWait();
            }
            String updatedPostal = null;
            if (!customerPostalCodeTextField.getText().isBlank()) {
                updatedPostal = customerPostalCodeTextField.getText();
            } else {
                emptyField.showAndWait();
            }

            int updatedState = customerStateProvinceComboBox.getSelectionModel().getSelectedItem().getStateProvinceIDNumber();


            if (addCustomer == true) {
                int stop = 0;
                try {
                    wesfritzc195pa.SQLMethods.CustomerSQL.addCustomer(updatedName, updatedAddress, updatedPhone, updatedPostal, updatedState);
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "General SQL Error, Check for Special Characters, Try Again.");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                    e.printStackTrace();
                    stop = e.getErrorCode();

                }

                if (stop != 0) {
                    // *************FIRST LAMBDA USED!************
                    // calls to firstLambdaInterface to set up a Customer addition information alert.
                    wesfritzc195pa.toolbox.firstLambdaInterface information = q -> "Customer " + q;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, information.displayNewInformation("successfully added!"));
                    alert.showAndWait();
                } else {

                }


            } else {
                int stop = 0;
                try {
                    int customerID = Integer.parseInt(customerIDTextField.getText());
                    wesfritzc195pa.SQLMethods.CustomerSQL.updateCustomer(updatedName, updatedAddress, updatedPhone, updatedPostal, updatedState, customerID);
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "General SQL Error, Check for Special Characters, Try Again.");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                    e.printStackTrace();
                    stop = e.getErrorCode();

                }
                if (stop != 0) {
                    // *************FIRST LAMBDA USED!************
                    // calls to firstLambdaInterface to set up a Customer update information alert.
                    wesfritzc195pa.toolbox.firstLambdaInterface information = q -> "Customer " + q;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, information.displayNewInformation("successfully updated!"));
                    alert.showAndWait();
                } else {

                }


            }
            customersTable.setItems(wesfritzc195pa.SQLMethods.CustomerSQL.selectToFillCustomerTable(connection));


            clearCustomerTextFields();

            customerStateProvinceComboBox.getSelectionModel().clearSelection();
            // Found runtime error, unable to clear combo box for Country without throwing a "field is null" error.
            //customerCountryComboBox.getSelectionModel().select(null);
            addCustomerRadioButton.setSelected(false);
            modifyCustomerRadioButton.setSelected(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**A method to delete a selected customer.
     * Also contains alerts and conditions pertaining to deletion.
     * @param event happens when the delete button is clicked after selecting a customer.*/
    @FXML
    public void onClickDeleteCustomer(ActionEvent event) throws IOException, SQLException {
        boolean m = modifyCustomerRadioButton.isSelected();
        if (m != true) {
            Alert mustbeinmodify = new Alert(Alert.AlertType.ERROR, "Must be in Modify Customer Mode to enter into Delete Mode", ButtonType.OK);
            mustbeinmodify.showAndWait();
            customersTable.getSelectionModel().clearSelection();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "This action will delete a Customer, are you sure?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            ButtonBar.ButtonData x = alert.getResult().getButtonData();
            if (!x.isCancelButton()) {
                ObservableList <Customer> customerToDelete = FXCollections.observableArrayList();
                customerToDelete.add(customersTable.getSelectionModel().getSelectedItem());
                System.out.println(customerToDelete);
                Connection connection = CustomerSQL.connectToDB();
                //query for if customer has appointments
                boolean appointments = CustomerSQL.lookupIfCustomerAppointments(connection, customerToDelete);
                if (appointments == true) {
                    Alert activeAppointment = new Alert(Alert.AlertType.ERROR, "Customer has appointments that must be cancelled first. Do you want to cancel the customer's appointments?", ButtonType.YES, ButtonType.NO);
                    activeAppointment.showAndWait();
                    ButtonBar.ButtonData c = activeAppointment.getResult().getButtonData();
                    if (!c.isCancelButton()) {
                        boolean customerAptsDeleted = AppointmentSQL.deleteAllCustomerAppointmentsByCustomerID(customerToDelete.get(0));
                        if (!customerAptsDeleted) {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR, "An error happened while deleting this customer's appointments.");
                            alert2.showAndWait();
                            return;
                        } else {
                            CustomerSQL.deleteCustomer(connection, customerToDelete);
                            clearCustomerTextFields();
                            customersTable.setItems(CustomerSQL.selectToFillCustomerTable(connection));
                            Alert deleteSuccess = new Alert(Alert.AlertType.INFORMATION, "Customer " + customerToDelete.get(0).getCustomerName() + " deleted.", ButtonType.OK);
                            deleteSuccess.showAndWait();
                        }
                    }


                } else {
                    CustomerSQL.deleteCustomer(connection, customerToDelete);
                    clearCustomerTextFields();
                    customersTable.setItems(CustomerSQL.selectToFillCustomerTable(connection));
                    Alert deleteSuccess = new Alert(Alert.AlertType.INFORMATION, "Customer " + customerToDelete.get(0).getCustomerName() + " deleted.", ButtonType.OK);
                    deleteSuccess.showAndWait();
                }
            } else {
                customersTable.getSelectionModel().clearSelection();
            }
        }
    }

    /**A method to connect with the combo box to select a country.
     * Unused currently -- what actually does this is in CustomersSQL.
     * @param event used when the country combo box is used.*/
    @FXML
    public void onSelectionPickCountry(ActionEvent event) throws IOException {

    }

    /**A method to connect with the combo box to select a state/province.
     * Unused currently -- what actually does this is in CustomersSQL.
     * @param event used when the state/province combo box is used.*/
    @FXML
    public void onSelectionPickStateProvince(ActionEvent event) throws IOException {

    }

    /**Method to begin the process of adding a customer.
     * Has to do with the radio buttons - selecting add or modify will modify this.
     * @param event Happens when the radio button for add is selected.*/
    @FXML
    public void onSelectionStartAddCustomer(ActionEvent event) throws IOException, SQLException {
        Connection connection = CustomerSQL.connectToDB();
        customersTable.getSelectionModel().clearSelection();
        clearCustomerTextFields();
        customerStateProvinceComboBox.getSelectionModel().clearSelection();
        customerCountryComboBox.setItems(CustomerSQL.selectToFillCountriesComboBox(connection));
        customerCountryComboBox.getSelectionModel().clearSelection();

        // customerCountryComboBox.getSelectionModel().select(0);
        customerCountryComboBox.setOnAction((select) -> {

            //Here, you fetch the filtering criteria for the State/Prov filtering function.
            int selectedCustomerCountryID = Integer.valueOf(customerCountryComboBox.getSelectionModel().getSelectedItem().getCountryID());

            //And now, we do the thing...
            try {
                //Make an Obs List to hold your filtered States, you will feed this back to the State combo box...
                ObservableList<StateProvince> filteredStatesByCountry = FXCollections.observableArrayList();

                //Fills the filtered list by way of the SQL query... this is where you pass in the criteria from above...
                filteredStatesByCountry.addAll(CustomerSQL.filterStatesByCountry(connection, selectedCustomerCountryID));

                //This refreshes the State combo box with the results from the filtering...
                customerStateProvinceComboBox.setItems(filteredStatesByCountry);



            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "General SQL Error, Check fields for Special Characters and try again");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();

            }
        });
    }

    /**Method to begin the process of updating a customer.
     * Has to do with the radio buttons - selecting add or modify will modify this.
     * @param event Happens when the radio button for modify is selected.*/
    @FXML
    public void onSelectionStartModifyCustomer(ActionEvent event) throws IOException, SQLException {
        //Sets the connection
        Connection connection = wesfritzc195pa.SQLMethods.CustomerSQL.connectToDB();
        ObservableList<Country> allCountries = wesfritzc195pa.SQLMethods.CustomerSQL.selectToFillCountriesComboBox(connection);
        //
        //Make a selectedCustomer object from the table, since the table was filled with customer objects
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        //System.out.println(selectedCustomer.getCustomerName());
        // Starting the logic...
        if (selectedCustomer != null) {


            //Make the temporary objects from their classes and the properties of the selectedCustomer...
            Country selectedCustomerCountry = new Country(selectedCustomer.getCustomerCountryID(), selectedCustomer.getCustomerCountry());
            StateProvince selectedCustomerState = new StateProvince(selectedCustomer.getCustomerStateProvinceID(), selectedCustomer.getCustomerStateProvince(), selectedCustomer.getCustomerCountryID());
            //Fill in the fields on the left of the customer view, I started you off, you finish it :-) <3
            customerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerID()));
            customerNameTextField.setText(selectedCustomer.getCustomerName());
            customerAddressTextField.setText(selectedCustomer.getCustomerAddress());
            customerPostalCodeTextField.setText(selectedCustomer.getCustomerPostalCode());
            customerPhoneNumberTextField.setText(selectedCustomer.getCustomerPhone());

            //Filling in the Combo Boxes based on the selections...
            customerCountryComboBox.setItems(allCountries);
            customerCountryComboBox.setValue(selectedCustomerCountry);
            customerStateProvinceComboBox.setItems(CustomerSQL.filterStatesByCountry(connection, selectedCustomerCountry.getCountryID()));
            customerStateProvinceComboBox.setValue(selectedCustomerState);

            //This part is a listener for a combo box.
            customerCountryComboBox.setOnAction((select) -> {

                //Here, you fetch the filtering criteria for the State/Prov filtering function.
                int selectedCustomerCountryID = customerCountryComboBox.getSelectionModel().getSelectedItem().getCountryID();

                //And now, we do the thing...
                try {
                    //Make an Obs List to hold your filtered States, you will feed this back to the State combo box...
                    ObservableList<StateProvince> filteredStatesByCountry = FXCollections.observableArrayList();

                    //Fills the filtered list by way of the SQL query... this is where you pass in the criteria from above...
                    filteredStatesByCountry.addAll(CustomerSQL.filterStatesByCountry(connection, selectedCustomerCountryID));

                    //This refreshes the State combo box with the results from the filtering...
                    customerStateProvinceComboBox.setItems(filteredStatesByCountry);

                    //This sets the text displayed to the Customer's state
                    customerStateProvinceComboBox.getEditor().setText(selectedCustomer.getCustomerStateProvince());


                } catch (SQLException ignore) {

                }
            });
            customersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                        if (newSelection != oldSelection) {
                            clearCustomerTextFields();
                            modifyCustomerRadioButton.setSelected(false);
                        } else {
                        }
                    });

//            while (selectedCustomer != null) {
//                Customer checkCustomer = customersTable.getSelectionModel().getSelectedItem();
//                if (selectedCustomer.getCustomerID() != checkCustomer.getCustomerID()) {
//                    clearCustomerTextFields();
//                    addCustomerRadioButton.setSelected(false);
//                    modifyCustomerRadioButton.setSelected(false);
//                    break;
//                } else {
//                }


            //Forces user to select a customer to modify... send back to add to restart trigger in this method.
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Select a Customer", ButtonType.OK);
            alert.showAndWait();
            modifyCustomerRadioButton.setSelected(false);
            addCustomerRadioButton.setSelected(false);
        }
    }

    /**Method to clear customer text fields.
     * This usually gets called after an add or modify.*/
    public void clearCustomerTextFields() {
        customerNameTextField.clear();
        customerIDTextField.clear();
        customerAddressTextField.clear();
        customerPostalCodeTextField.clear();
        customerPhoneNumberTextField.clear();
        customerStateProvinceComboBox.getEditor().clear();
        customerCountryComboBox.getEditor().clear();

    }
}
