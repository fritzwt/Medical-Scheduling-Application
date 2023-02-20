package wesfritzc195pa.toolbox;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**This class handles the action behind appending new login information to the file login_activity.txt.
 */
public class loginFileHandler {

    /**This method begins the act of the file sequence.
     * First finds the file when a valid login happens.
     * @param validationText A valid login.
     * @throws IOException
     */
    public static void loginFileDoer(String validationText) throws IOException {
        // Bit that logs user info for logins *******************************
        // Filename and item variable
        String filename = "login_activity.txt", String;

        //create FileWriter object
        FileWriter fwriter = new FileWriter(filename, true);

        // Create an open file
        // if this file has already been created, it is overwritten
        PrintWriter outputFile = new PrintWriter(fwriter);

       outputFile.println(validationText);

        // close file
        outputFile.close();
        System.out.println("File written!");
    }
}
