package il.ac.hit.java.costmanagerapp.model;

import static il.ac.hit.java.costmanagerapp.model.utils.AppUtils.checkArgument;

public class Category {
    public static String MESSAGE_CATEGORY_CONSTRAINTS =
            "Category name should not be blank. It should be alphanumeric.";

    public static String CATEGORY_VALIDATION_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9]*$";

    public static String[] INITIAL_CATEGORIES = {"Food", "Transportation", "School", "Bills", "House",
            "Insurance", "Entertainment", "Medical", "Travel", "Tax", "Car", "General"};

    private String categoryName;

    public Category(String category) {
        checkArgument(isValidCategory(category), MESSAGE_CATEGORY_CONSTRAINTS);
        setCategoryName(category);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static boolean isValidCategory(String test) {
        return test.matches(CATEGORY_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Category
                && categoryName.equals(((Category) other).categoryName));
    }
}