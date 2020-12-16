package il.ac.hit.java.costmanagerapp.model;

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
}