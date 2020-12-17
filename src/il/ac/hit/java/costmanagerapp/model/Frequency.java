package il.ac.hit.java.costmanagerapp.model;

public enum Frequency {
    ONE_TIME(1),
    MONTHLY(2),
    YEARLY(3);

    private int id;

    Frequency(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

// enum
/*
public class Frequency {

    private int id;
    private String name;

    public Frequency(int id, String name) {
        setId(id);
        setFreq(name);
    }

    public String getFreq() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFreq(String freqName) {
        this.name = freqName;
    }
}*/
