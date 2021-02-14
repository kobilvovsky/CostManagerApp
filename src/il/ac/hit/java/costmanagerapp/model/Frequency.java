package il.ac.hit.java.costmanagerapp.model;

public enum Frequency {
    ONE_TIME(0),
    MONTHLY(1),
    YEARLY(2);

    private int id;

    /**
     * frequency constructor
     * @param id frequency type
     */
    Frequency(int id) {
        setId(id);
    }

    /**
     * Gets frequency object type
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets frequency type as string
     * @param id frequency type
     * @return frequency title
     */
    public static String stringToFrequency(String id) {
        return (Frequency.values()[Integer.parseInt(id)]).toString();
    }

    /**
     * Sets frequency type
     * @param id frequency type
     */
    public void setId(int id) {
        this.id = id;
    }
}