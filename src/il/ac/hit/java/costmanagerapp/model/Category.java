package il.ac.hit.java.costmanagerapp.model;

public class Category {

    private String categoryName;

    public enum E_CAT {
        HOUSE,
        INSURANCE,
        DINING,
        ENTERTAINMENT,
        SHOPPING,
        MEDICAL,
        TRAVEL,
        GROCERIES,
        CAR,
        TAX,
        OTHERS,
        GENERAL
    }

    public Category(String name) {
        setCategoryName(name);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}