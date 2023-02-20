package wesfritzc195pa.toolbox;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

import java.sql.Timestamp;
import java.time.*;

/**This method handles the time conversions between user computer, database, and application.
 */
public class TimeConversions {

    /**This method creates a UTC time to talk to SQL from a user's computer
     * @param date Date used in a timestamp to converse with the database.
     * @param time Time used in a timestamp to converse with the database.
     * @return Returns time used in the database.
     */
    public static Timestamp timestampUTC(LocalDate date, LocalTime time) {
        //First, convert to LocalDateTime object class
        LocalDateTime inputLDT = LocalDateTime.of(date, time);
        //Now, add zone info
        ZonedDateTime inputZDT = inputLDT.atZone(ZoneId.systemDefault());
        //Convert to UTC
        OffsetDateTime inputUTC = inputZDT.toInstant().atOffset(ZoneOffset.UTC);
        //Convert to TimeStamp for SQL Input, must convet back to LocalDateTime class object for this...
        Timestamp sqlTime = Timestamp.valueOf(inputUTC.toLocalDateTime());
        // send the value back to the calling page.
        return sqlTime;
    }

    /**This method adjusts SQL time to the local time of the user's computer.
     * @param sqlTS Time of the current timestamp.
     * @return Returns a localized time for the user to see.
     */
    public static LocalDateTime sqlTimeToLocal(Timestamp sqlTS) {
        //First, convert input timestamp to a zoned date time, for secure time conversion, must set this to UTC
//        ZoneId User = ZoneId.systemDefault();
//        ZoneId UTC = ZoneId.of("UTC");
//        System.out.println(User);
//        LocalDateTime sqltimetoLDT = sqlTS.toLocalDateTime();
//        System.out.println(sqltimetoLDT);
//        ZonedDateTime ldtToUTC = sqltimetoLDT.atZone(UTC);
//        System.out.println(ldtToUTC);
//        ZonedDateTime UTCtoUser = ldtToUTC.withZoneSameInstant(User);
//        System.out.println(UTCtoUser);
//        LocalDateTime localizedTime = UTCtoUser.toLocalDateTime();
//        System.out.println(localizedTime);
        OffsetDateTime inputODT = sqlTS.toInstant().atOffset(ZoneOffset.UTC);
       System.out.println(inputODT);
        // ZonedDateTime inputZDT = time.toLocalDateTime().atZone(ZoneId.of("UTC"));
        // Now, adjust from UTC to Local Time Zone
        ZonedDateTime inputLDT = inputODT.atZoneSameInstant(ZoneId.systemDefault());
        System.out.println(inputLDT);
        // Convert the offset time into the Local Date Time Class
        LocalDateTime localizedTime = inputLDT.toLocalDateTime();
        System.out.println(localizedTime);
        // And sent it back to the calling page...
        return localizedTime;
    }

    /**This method adjusts the datetime from the local time to the business' time.
     * Not currently used, but kept in for testing/troubleshooting purposes.
     * @param date Date of local computer.
     * @param time Time of local computer.
     * @return Returns offset date/time for the business time.
     */
    public static OffsetDateTime localToBusinessZone(LocalDate date, LocalTime time) {
        //First make a LocalDateTime object from the two input parameters...
        LocalDateTime ldt  = LocalDateTime.of(date, time);
        //Adjust the ldt to incorporate local time zone because LocalDateTime iss timezone blind by itself...
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        //Adjust to Business time Zone
        OffsetDateTime odt = OffsetDateTime.from(zdt.toInstant().atZone(ZoneId.of("America/New_York")));
        return odt;
    }

    /**This method validates whether start and end times are okay for an appointment.
     * @param date Date of the appointment.
     * @param start Start time of the appointment.
     * @param end End time of the appointment.
     * @return Returns true or false based on wether the appointment times are okay or not.
     */
    public static boolean validateAppointmentStartEndOK(LocalDate date, LocalTime start, LocalTime end) {
        int weekStarts = DayOfWeek.MONDAY.getValue();
        int weekEnds = DayOfWeek.FRIDAY.getValue();
        DayOfWeek dateToCheck = DayOfWeek.from(date);

        LocalTime businessStart = LocalTime.from(ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.systemDefault()));
        LocalTime businessEnd = LocalTime.from(ZonedDateTime.of(LocalDate.now(), LocalTime.of(22, 0), ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.systemDefault()));
        if (dateToCheck.getValue() < weekStarts || dateToCheck.getValue() > weekEnds) {
            Alert dayInvalid = new Alert(Alert.AlertType.ERROR, "Appointment Date is outside of Business Days, Monday - Friday, " +
                    "please choose a valid date.", ButtonType.OK);
            dayInvalid.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            dayInvalid.showAndWait();
            return false;
        } else {

            if (start.isBefore(businessStart) || start.isAfter(businessEnd)) {
                Alert startInvalid = new Alert(Alert.AlertType.ERROR, "Appointment Start Time is outside Business Hours, 8 AM - 10 PM Eastern, " +
                        "please choose a valid start time", ButtonType.OK);
                startInvalid.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                startInvalid.showAndWait();
                return false;
            } else {
                if (end.isBefore(businessStart) || end.isAfter(businessEnd)) {
                    Alert endInvalid = new Alert(Alert.AlertType.ERROR, "Appointment End Time is outside Business Hours, 8 AM - 10 PM Eastern, " +
                            "please choose a valid end time.");
                    endInvalid.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    endInvalid.showAndWait();
                    return false;

                } else {
                    return true;
                }

            }
        }
    }


}
