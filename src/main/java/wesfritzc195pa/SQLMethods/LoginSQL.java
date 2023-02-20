package wesfritzc195pa.SQLMethods;

import wesfritzc195pa.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wesfritzc195pa.models.SystemUser;
import wesfritzc195pa.toolbox.loginFileHandler;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**This class talks handles the queries made to the database to handle the application's logins.
 */
public class LoginSQL {

    /**This method validates the user depending on their login.
     * Contains text that is appended to login_activity.txt regardless the login being good or not.
     * @param unToCheck Username that needs to be checked.
     * @param pwToCheck Password that needs to be checked.
     * @return Returns text dealing with the correct or incorrect login for tacking on to .txt document.
     * @throws SQLException
     * @throws IOException
     */
    public static boolean validateUser(String unToCheck, String pwToCheck) throws SQLException, IOException {

        System.out.println("Starting Validation");
        OffsetDateTime time = OffsetDateTime.now(ZoneId.of("UTC"));
        ObservableList<SystemUser> validUsers = FXCollections.observableArrayList();
        System.out.println("Starting Query");
        String query = "SELECT * FROM client_schedule.users WHERE User_Name = '" + unToCheck + "';";
        PreparedStatement ps = JDBC.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("User_Name");
            String password = rs.getString("Password");
            SystemUser user = new SystemUser(name, password, time, "validateonly");
            validUsers.add(user);
        }
        SystemUser userToValidate = new SystemUser(unToCheck, pwToCheck, time, "validateonly");
        boolean b = false;
        String validationText = "User '" + userToValidate.getName() + "' unsuccessful at logging in. Attempt made at " + time + ".";
        for (SystemUser validUser : validUsers) {
            System.out.println(validUser.getName());
            System.out.println(userToValidate.getName());
            if (validUser.getName().contains(userToValidate.getName()) &&
                    validUser.getPassword().contains(userToValidate.getPassword())) {
                System.out.println("User Validated");
                b = true;
                validationText = "User '" + validUser.getName() + "' successfully logged in at " + time + ".";

            } else {
                System.out.println("Unable to Validate");
                b = false;
                validationText = "User '" + userToValidate.getName() + "' unsuccessful at logging in. Attempt made at " + time + ".";
            }
        }
        System.out.println(b);
        System.out.println(validationText);
        loginFileHandler.loginFileDoer(validationText);
        return b;
    }

    /** Creates an observable list of IDs of the users for the application.
     * Used to help make a combo box on the appointments page function.
     * @return Returns a list of user IDs for the appointments page combo box.
     * @throws SQLException
     */
    public static ObservableList<Integer> getUserIDs() throws SQLException {
        ObservableList<Integer> IDs = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client_schedule.users;";
        PreparedStatement ps = JDBC.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int x = rs.getInt("User_ID");
            IDs.add(x);
       }
        return IDs;
    }

}


