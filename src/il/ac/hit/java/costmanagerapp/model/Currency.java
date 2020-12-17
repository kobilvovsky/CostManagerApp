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

/*

public class Currency {

    private int id;
    private String name;

    public Currency(int id, String name) {
        setId(id);
        setCurrency(name);
    }

    public String getCurrency() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrency(String name) {
        this.name = name;
    }
}*/
