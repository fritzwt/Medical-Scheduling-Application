package wesfritzc195pa.toolbox;

/**This creates my second lambda expression interface.
 * Used by being called to create a confirmation alert asking the user if they want to go back to the directory.
 * This Lambda method makes my code better, because it is used to help with the sheer amount of alerts in this application.
 * This Lambda handles this type of confirmation alert, and can easily be modified to handle other similar situations.
 */
public interface secondLambdaInterface {


    // used to make the command to call a confirmation alert with a confirmation button to return to the directory shorter.
    // for onClickCancel/onActionCancel/onActionBackToDirectory for customers/appointments/reports, respectively.

    /**Method to display new confirmation alerts when called as a lambda expression.
     * @param s string pertaining to what the confirmation alert wants to say, confirming the move back to the directory in this lambda's case.
     */
    String getMessage(String s);
}
