package wesfritzc195pa.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Class that holds information pertaining to creating contact objects.*/
public class Contact {
    private int contactID;
    private String contactName;
    private String contactEmail;

    /**This is the method that is invoked when referring to contact objects.
     * This was more used to assign contacts to appointments, rather than creating contacts.
     * @param contactID ID number for the contact.
     * @param contactName Name for the contact.
     * @param contactEmail Email for the contact. */
    public Contact(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**Creates a list of all contacts by name.
     * Makes it easy for the entire list to be called to parse data from later.
     * @return all contacts by name.*/
    public static ObservableList<String> allContactsByName(ObservableList<Contact> allContacts) {
        ObservableList<String> allContactsByName = FXCollections.observableArrayList();
        for (Contact contact : allContacts) {
            String name = contact.getContactName();
            allContactsByName.add(name);
        } return allContactsByName;
    }

    /**Method that gets the contact ID
     * Unused due to contents in ContactsSQL.
     * @return ID number of the contact.*/
    public int getContactID() {
        return contactID;
    }

    /**Method that sets the contact ID
     * Unused due to database involvement.
     * @param contactID the ID of the contact.*/
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**Method that gets the contact name
     * @return the name of the contact.*/
    public String getContactName() {
        return contactName;
    }

    /**Method that sets the contact name.
     * Unused due to database involvement.
     * @param contactName the name of the contact.*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**Method that gets the contact email.
     * Unused due to contents in ContactsSQL.
     * @return email address of the contact.*/
    public String getContactEmail() {
        return contactEmail;
    }

    /**Method that sets the contact email.
     * Unused due to database involvement.
     * @param contactEmail the email of the contact.*/
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }


}
