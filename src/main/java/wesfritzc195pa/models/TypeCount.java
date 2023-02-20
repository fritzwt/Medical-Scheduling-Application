package wesfritzc195pa.models;

/**This class creates objects for the reports page, so a table can be filled.
 * The table pertains to the count of specific types of appointments.
 */
public class TypeCount {
    private String type;
    private Integer count;

    /** This Method creates the ability for type count objects to be made.
     * The objects get used in the reports page Type Count table.
     * @param type The type of appointment it pertains to.
     * @param count The number of the specific type of appointment.
     */
    public TypeCount(String type, int count) {
        this.type = type;
        this.count = count;
    }

    /** A method to get the type of appointment for the type count table.
     * @return Returns the type of appointment.
     */
    public String getType() {
        return type;
    }

    /** A method to set the type of appointment for the type count table.
     * @param type The type of appointment.
     */
    public void setType(String type) {
        this.type = type;
    }

    /** A method to get the count of the number of appointments having a specific type.
     * @return Returns a number resembling the count of appointments with that type.
     */
    public Integer getCount() {
        return count;
    }

    /** A method to set the count of the number of appointments having a specific type.
     * Unused due to database involvement.
     * @param count The count of the number of appointments having a specific type.
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}
