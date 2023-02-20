package wesfritzc195pa.models;

import java.time.OffsetDateTime;

/**Class that holds information pertaining to creating user objects and logins.
 * Much of this class interacts with LoginSQL*/
public class SystemUser {
    private String name;
    private String password;

    private OffsetDateTime time;

    private String succFail;

    /**Method that retrieves the offset time for logins
     * @return the offset time.
     */
    public OffsetDateTime getTime() {
        return time;
    }

    /**Method that sets the offset time for logins
     * @param time The time that user logins are set to.
     */
    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    /**Method that retrieves the success or fail status for logins.
     * Unused due to LoginSQL involvement.
     * @return the success or failure.
     */
    public String getSuccFail() {
        return succFail;
    }

    /**Method that sets the status of success or failure for logins.
     * Unused due to LoginSQL involvement.
     * @param succFail the success or failure of a login.
     */
    public void setSuccFail(String succFail) {
        this.succFail = succFail;
    }

    /**This is the method that is invoked when interacting with a user object.
     * @param name Name of the user.
     * @param password Password of the user.
     * @param time Time of the user's login.
     * @param succFail Whether the user succeeded at logging in or not.
     */
    public SystemUser(String name, String password, OffsetDateTime time, String succFail) {
        this.name = name;
        this.password = password;
        this.time = time;
        this.succFail = succFail;
    }

    /**Method that gets the name of the user.
     * @return name of the user.
     */
    public String getName() {
        return name;
    }

    /**Method that sets the name of the user.
     * @param name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Method that gets the password of the user.
     * @return password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**Method that sets the password of the user.
     * @param password password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
