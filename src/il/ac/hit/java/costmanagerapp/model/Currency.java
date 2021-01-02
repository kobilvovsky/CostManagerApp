package il.ac.hit.java.costmanagerapp.model;

public enum Currency {
    USD(1),
    EURO(2),
    YEN(3),
    POUND(4),
    NIS(5);

    private int id;

    Currency(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}