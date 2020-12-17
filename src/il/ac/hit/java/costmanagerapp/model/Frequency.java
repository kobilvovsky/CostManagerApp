package il.ac.hit.java.costmanagerapp.model;

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
}