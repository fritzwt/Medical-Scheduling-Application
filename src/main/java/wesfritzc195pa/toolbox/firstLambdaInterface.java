package wesfritzc195pa.toolbox;

/**This creates my first Lambda expression interface.
 * Used by being called to create an informational alert informing the user of a successful customer/appointment add/update.
 * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
 * This Lambda handles this type of informational alert, and can easily be modified to handle other similar situations.
 */
public interface firstLambdaInterface {

    // used to make the command to call an informational alert informing the user of a successful customer/appointment add/update.
    // for use with successfully adding and modifying customers and appointments.

    /**Method to display new informational alerts when called as a lambda expression.
     * @param q string pertaining to what the informational alert wants to say.
     */
    String displayNewInformation(String q);
}
