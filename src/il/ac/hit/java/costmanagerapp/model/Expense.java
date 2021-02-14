package il.ac.hit.java.costmanagerapp.model;
import java.util.Date;

public class Expense {
    private double cost;
    private Category category;
    private Currency currency;
    private String description;
    private Date creationDate;
    private Date dueDate;
    private Frequency type;

    /**
     * Expense constructor
     * @param cost cost of the expense
     * @param category category of the expense
     * @param currency currency of the expense
     * @param description description of the expense
     * @param dueDate due date of the expense
     * @param type payment type of the expense
     */
    public Expense(double cost, String category, Currency currency, String description, String dueDate, Frequency type) {
        setCost(cost);
        setCategory(category);
        setCurrency(currency);
        setDescription(description);
        setCreationDate();
        setDueDate(dueDate);
        setType(type);
    }

    /**
     * Gets the cost of the expense
     * @return cost of the expense
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Cost setter
     * Rounds the expense cost to .2 decimal digits
     * @param cost cost of the expense
     */
    public void setCost(double cost) {
        double c = Math.round(cost * 100);
        double d = c/100;
        this.cost = d;
    }

    /**
     * Gets the expense category
     * @return category of the expense
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Sets the category of the expense by category's name
     * @param category category of the expense
     */
    public void setCategory(String category) {
        this.category = new Category(category);
    }

    /**
     * Gets the currency of the expense
     * @return currency of the expense
     */
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * Sets the currency of the expense
     * @param currency currency type
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Gets the description of the expense
     * @return description of the expense
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the expense
     * @param description description of the expense
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of creation of the expense
     * @return date of the expense the day it was created
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Gets due date of the expense
     * @return due date of the expense
     */
    public Date getDueDate() {
        return this.dueDate;
    }

    /**
     * Sets due date of the expense
     * @param dueDate due date of the expense
     */
    public void setDueDate(String dueDate) {
        this.dueDate = java.sql.Date.valueOf(dueDate);
    }

    /**
     * Generates current date
     */
    public void setCreationDate() { this.creationDate = new java.sql.Date(System.currentTimeMillis()); }

    /**
     * Gets the frequency of the expense
     * @return frequency of the expense
     */
    public Frequency getType() {
        return this.type;
    }

    /**
     * Sets the frequency of the expense
     * @param type frequency of the expense
     */
    public void setType(Frequency type) {
        this.type = type;
    }

    /**
     * Prints expense data
     * @return expense object as string
     */
    @Override
    public String toString() {
        return "Expense{" +
            ", cost=" + getCost() +
            ", category=" + getCategory().getCategoryName() +
            ", currency=" + getCurrency() +
            ", description='" + getDescription() + '\'' +
            ", creationDate=" + getCreationDate() +
            ", dueDate=" + getDueDate() +
            ", type=" + getType() +
            '}';
    }
}
