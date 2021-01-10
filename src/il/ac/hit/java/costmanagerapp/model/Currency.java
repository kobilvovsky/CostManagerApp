package il.ac.hit.java.costmanagerapp.model;

public enum Currency {
    USD(0),
    EURO(1),
    YEN(2),
    POUND(3),
    NIS(4);

    private int id;

    Currency(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public static String stringToCurrency(String id) {
        return (Currency.values()[Integer.parseInt(id)]).toString();
    }

    public void setId(int id) {
        this.id = id;
    }
}