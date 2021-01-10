package il.ac.hit.java.costmanagerapp.model;

public enum Frequency {
    ONE_TIME(0),
    MONTHLY(1),
    YEARLY(2);

    private int id;

    Frequency(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public static String stringToFrequency(String id) {
        return (Frequency.values()[Integer.parseInt(id)]).toString();
    }

    public void setId(int id) {
        this.id = id;
    }
}