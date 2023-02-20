package wesfritzc195pa.models;

/**Class that holds information pertaining to creating state/province objects.*/
public class StateProvince {

    private String stateProvinceName;
    private int stateProvinceIDNumber;
    private int countryIDNumber;

    /**Method that retrieves the state/province name of the state/province.
     * Unused due information being parsed via SQL statements.
     * @return the name of the state/province.
     */
    public String getStateProvinceName() {
        return stateProvinceName;
    }

    /**Method to retrieve the ID number of the state/province.
     * @return the ID number of the state/province.
     */
    public int getStateProvinceIDNumber() {return stateProvinceIDNumber;}

    /**Method to retrieve the ID number of the country.
     * Unused due information being parsed via SQL statements.
     * @return
     */
    public int getCountryIDNumber() {return countryIDNumber;}

    /**This is the method that is invoked when interacting with State/Province objects.
     * @param stateProvinceIDNumber ID number of the state/province.
     * @param stateProvinceName Name of the state/province.
     * @param countryIDNumber ID number of the country that the state/province resides in.
     */
    public StateProvince(int stateProvinceIDNumber, String stateProvinceName, int countryIDNumber) {
        this.stateProvinceIDNumber =stateProvinceIDNumber;
        this.stateProvinceName = stateProvinceName;
        this.countryIDNumber=countryIDNumber;
    }

    /**Method to make the state/province ID and the name to swap information properly
     * @return the name of the state/province in a string format.
     */
    @Override
    public String toString() {
        return (stateProvinceName);
    }
}