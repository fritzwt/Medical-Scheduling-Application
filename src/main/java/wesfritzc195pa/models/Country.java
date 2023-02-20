package wesfritzc195pa.models;

/**Class that holds information pertaining to creating country objects.*/
public class Country {

    private int countryID;
    private String countryName;

    /**Method that gets the country name.
     * Unused due to content in CustomerSQL.
     * @return name of country.*/
    public String getCountryName() {
        return countryName;
    }

    /**Method that sets the country name.
     * Unused due to database involvement.
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**Method that gets the country ID number.
     * Unused due to content in CustomerSQL.
     * @return ID number of country.*/
    public int getCountryID() {
        return countryID;
    }

    /**Method that is invoked when referencing country objects.
     * @param countryID
     * @param countryName
     */
    public Country(int countryID, String countryName) {
       this.countryID = countryID;
        this.countryName = countryName;
    }

    /**Method that converts countryID types to the string versions of their data.
     * @return name of the country in string form.
     */
    @Override
    public String toString() {
        return (countryName);
    }
}