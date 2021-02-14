package il.ac.hit.java.costmanagerapp.model;

import static il.ac.hit.java.costmanagerapp.model.utils.AppUtils.checkArgument;

public class Category {
    public static String MESSAGE_CATEGORY_CONSTRAINTS =
            "Category name should not be blank. It should be alphanumeric.";

    public static String CATEGORY_VALIDATION_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9]*$";

    public static String[] INITIAL_CATEGORIES = {"Food", "Transportation", "School", "Bills", "House",
            "Insurance", "Entertainment", "Medical", "Travel", "Tax", "Car", "General"};

    private String categoryName;

    /**
     * Category constructor
     * @param category category as text
     */
    public Category(String category) {
        checkArgument(isValidCategory(category), MESSAGE_CATEGORY_CONSTRAINTS);
        setCategoryName(category);
    }

    /**
     * Gets category's name
     * @return category's name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets category's name
     * @param categoryName category's name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Checks if category name is valid
     * @param name category name
     * @return true if valid, otherwise false
     */
    public static boolean isValidCategory(String name) {
        return name.matches(CATEGORY_VALIDATION_REGEX);
    }

    /**
     * Checks if category object is the same as another category object
     * @param other category object
     * @return true if same, otherwise false
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Category
                && categoryName.equals(((Category) other).categoryName));

    }
}