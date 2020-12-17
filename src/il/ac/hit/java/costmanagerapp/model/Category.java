package il.ac.hit.java.costmanagerapp.model;

public class Category {

    private int id;
    private String categoryName;

    public Category(int id, String name) {
        setId(id);
        setCategoryName(name);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}