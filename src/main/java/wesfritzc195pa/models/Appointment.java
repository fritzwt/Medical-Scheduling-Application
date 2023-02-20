package wesfritzc195pa.models;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.LocalTime;

/**Class that holds information pertaining to creating appointment objects.*/
public class Appointment {
    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalTime appointmentStartDT;
    private LocalTime appointmentEndDT;

    private LocalDate datePicker;
    private int appointmentCustomerID;
    private int appointmentStaffID;
    private int appointmentContactID;

    /**This is the method that is invoked when creating a new appointment or modifying an appointment.
     * @param appointmentID ID number of the appointment.
     * @param appointmentTitle title of the appointment.
     * @param appointmentDescription short description of the appointment.
     * @param appointmentLocation location of the appointment.
     * @param appointmentType type of the appointment - Planning Session, De-Briefing, or other.
     * @param datePicker Picks the date of the appointment. Gets used in a combo box.
     * @param appointmentStartDT start time of the appointment.
     * @param appointmentEndDT end time of the appointment.
     * @param appointmentCustomerID customer id pertaining to the appointment.
     * @param appointmentContactID the contact id with whom the appointment is with.
     * @param appointmentStaffID the user that is using the application/setting the appointment.*/
    public Appointment(int appointmentID, String appointmentTitle, String appointmentDescription,
                       String appointmentLocation, String appointmentType, LocalDate datePicker, LocalTime appointmentStartDT, LocalTime appointmentEndDT, int appointmentCustomerID, int appointmentStaffID, int appointmentContactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStartDT = appointmentStartDT;
        this.appointmentEndDT = appointmentEndDT;
        this.datePicker = datePicker;
        this.appointmentCustomerID = appointmentCustomerID;
        this.appointmentStaffID = appointmentStaffID;
        this.appointmentContactID = appointmentContactID;
    }

    /**Method that gets the information for the date picker.
     * @return datePicker the information selects the date.*/
    public LocalDate getDatePicker() {
        return datePicker;
    }

    /**Method that sets the information for the date picker.
     * Unused due to database involvement.
     * @param datePicker the datePicker to set.*/
    public void setDatePicker(LocalDate datePicker) {
        this.datePicker = datePicker;
    }

    /**Method that gets the contact ID for the appointment.
     * @return the contact ID for the appointment.*/
    public int getAppointmentContactID() {
        return appointmentContactID;
    }

    /**Method that sets the contact ID for the appointment.
     * Unused due to database involvement.
     * @param appointmentContactID the contact ID to set.*/
    public void setAppointmentContactID(int appointmentContactID) {
        this.appointmentContactID = appointmentContactID;
    }

    /**Method that gets the appointment ID for the appointment.
     * @return the ID number pertaining to the appointment.*/
    public int getAppointmentID() {
        return appointmentID;
    }

    /**Method that sets the appointment ID for the appointment.
     * Unused due to database involvement.
     * @param appointmentID the appointment ID to set.*/
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**Method that gets the appointment title for the appointment.
     * @return the appointment's title.*/
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**Method that sets the title for the appointment.
     * Unused due to database involvement.
     * @param appointmentTitle the appointment title to set.*/
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**Method that gets the appointment's description.
     * @return the appointment's description.*/
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**Method that sets the description for the appointment.
     * Unused due to database involvement.
     * @param appointmnetDescription the appointment description to set.*/
    public void setAppointmentDescription(String appointmnetDescription) {
        this.appointmentDescription = appointmnetDescription;
    }

    /**Method that gets the location for the appointment.
     * @return the appointment's location.*/
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**Method that sets the location for the appointment.
     * Unused due to database involvement.
     * @param appointmentLocation the location to set. */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**Method that gets the type for the appointment.
     * @return the type of appointment.*/
    public String getAppointmentType() {
        return appointmentType;
    }

    /**Method that sets the type of appointment.
     * Unused due to database involvement.
     * @param appointmentType the type of appointment.*/
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**Method that gets the start date/time for the appointment.
     * @return the start date/time for the appointment.*/
    public LocalTime getAppointmentStartDT() {
        return appointmentStartDT;
    }

    /**Method that sets the start date/time for the appointment.
     * Unused due to database involvement.
     * @param appointmentStartDT the start date/time for the appointment.*/
    public void setAppointmentStartDT(LocalTime appointmentStartDT) {
        this.appointmentStartDT = appointmentStartDT;
    }

    /**Method that gets the end date/time for the appointment.
     * @return the end date/time for the appointment.*/
    public LocalTime getAppointmentEndDT() {
        return appointmentEndDT;
    }

    /**Method that sets the end date/time for the appointment.
     * Unused due to database involvement.
     * @param appointmentEndDT the end date/time for the appointment.*/
    public void setAppointmentEndDT(LocalTime appointmentEndDT) {
        this.appointmentEndDT = appointmentEndDT;
    }

    /**Method that gets the customer ID for the appointment.
     * @return the customer ID associated with the appointment.*/
    public int getAppointmentCustomerID() {
        return appointmentCustomerID;
    }

    /**Method that sets the customer ID associated with the appointment.
     * Unused due to database involvement.
     * @param appointmentCustomerID the customer ID for the appointment.*/
    public void setAppointmentCustomerID(int appointmentCustomerID) {
        this.appointmentCustomerID = appointmentCustomerID;
    }

    /**Method that gets the user ID for the appointment.
     * @return the user ID associated with the application use at the time.*/
    public int getAppointmentStaffID() {
        return appointmentStaffID;
    }

    /**Method that sets the user ID for the appointment.
     * Unused due to database involvement.
     * @param appointmentStaffID the user ID for the appointment.*/
    public void setAppointmentStaffID(int appointmentStaffID) {
        this.appointmentStaffID = appointmentStaffID;
    }
}
