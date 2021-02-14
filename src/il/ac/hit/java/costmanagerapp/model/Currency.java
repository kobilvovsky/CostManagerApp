/*
    Submitted by:
    Erez Mizrahi 316267087
    Lvovsky Yakov 315825380
    Netanel Tarnorudsky 315424689
 */

package il.ac.hit.java.costmanagerapp.model;

public enum Currency {
    USD(0),
    EURO(1),
    YEN(2),
    POUND(3),
    NIS(4);

    private int id;

    /**
     * Currency constructor
     * @param id currency type
     */
    Currency(int id) {
        setId(id);
    }

    /**
     * Gets currency type
     * @return currency type
     */
    public int getId() {
        return id;
    }

    /**
     * Converts currency type to string
     * @param id currency type
     * @return currency type as text
     */
    public static String stringToCurrency(String id) {
        return (Currency.values()[Integer.parseInt(id)]).toString();
    }

    /**
     * Sets the type of the currency
     * @param id type of the currency
     */
    public void setId(int id) {
        this.id = id;
    }
}