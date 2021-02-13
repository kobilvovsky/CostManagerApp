package il.ac.hit.java.costmanagerapp.model;
import java.util.Date;

public class Expense {
    //private int id;
    private int ownerID;
    private double cost; // cost of expense
    private Category category;
    private Currency currency;
    private String description;
    private Date creationDate;
    private Date dueDate;
    private Frequency type;

    /**
     * Expense constructor
     * @param ownerId userId who made the expense
     * @param cost cost of the expense
     * @param category category of the expense
     * @param currency currency of the expense
     * @param description description of the expense
     * @param dueDate due date of the expense
     * @param type payment type of the expense
     */
    public Expense(int ownerId, double cost, String category, Currency currency, String description, String dueDate, Frequency type) {
        //setId(id);
        setOwner(ownerId);
        setCost(cost);
        setCategory(category);
        setCurrency(currency);
        setDescription(description);
        this.creationDate = new java.sql.Date(System.currentTimeMillis());
        setDueDate(dueDate);
        setType(type);
    }

    /**
     * Gets userId of the user who created the expense
     * @return userId
     */
    public int getOwner() {
        return ownerID;
    }

    /**
     * Sets the owner of the expense
     * @param ownerID userId of the user who made the expense
     */
    public void setOwner(int ownerID) {
        this.ownerID = ownerID;
    }

/*    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    /**
     * Gets the cost of the expense
     * @return cost of the expense
     */
    public double getCost() {
        return cost;
    }

    /**
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
        return category;
    }

    /**
     * Sets the category of the expense
     * @param category category of the expense
     */
    public void setCategory(String category) {
        Category c=new Category(category);
        this.category = c;
    }

    /**
     * Gets the currency of the expense
     * @return currency of the expense
     */
    public Currency getCurrency() {
        return currency;
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
        return description;
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
        return creationDate;
    }

    /**
     * Gets due date of the expense
     * @return due date of the expense
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets due date of the expense
     * @param dueDate due date of the expense
     */
    public void setDueDate(String dueDate) {
        this.dueDate = java.sql.Date.valueOf(dueDate);
    }

    /**
     * Gets the frequency of the expense
     * @return frequency of the expense
     */
    public Frequency getType() {
        return type;
    }

    /**
     * Sets the frequency of the expense
     * @param type frequency of the expense
     */
    public void setType(Frequency type) {
        this.type = type;
    }

    /**
     * Prints expense variables data
     * @return expense object as string
     */
    @Override
    public String toString() {
        return "Expense{" +
                "ownerID=" + getOwner() +
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
