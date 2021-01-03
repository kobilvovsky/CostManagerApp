package il.ac.hit.java.costmanagerapp.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Expense(/*int id, */int ownerId, double cost, Category category, Currency currency, String description, String dueDate, Frequency type) {
        //setId(id);
        setOwner(ownerId);
        setCost(cost);
        setCategory(category);
        setCurrency(currency);
        setDescription(description);
        setCreationDate();
        setDueDate(dueDate);
        setType(type);
    }

    public int getOwner() {
        return ownerID;
    }

    public void setOwner(int ownerID) {
        this.ownerID = ownerID;
    }

/*    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public double getCost() {
        return cost;
    }

    //Rounding number to .2 decimal digits
    public void setCost(double cost) {

        this.cost = Math.round(cost*100)/100;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getSum() { // total of expense over period of time (monthly, yearly)
        switch (getType()) {
            case YEARLY:
                return Math.round((getCost() * 12)*100)/100;
        }
        return getCost();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() { // ex: 2020-12-17
        this.creationDate = new java.sql.Date(System.currentTimeMillis());
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {

        try {
            this.dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dueDate);
        } catch (ParseException e) { //add relevant exception
            e.printStackTrace();
        }
    }

    public Frequency getType() {
        return type;
    }

    public void setType(Frequency type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Expense{" +
                //"id=" + getId() +
                "ownerID=" + getOwner() +
                ", cost=" + getCost() +
                ", category=" + getCategory() +
                ", sum=" + getSum() +
                ", currency=" + getCurrency() +
                ", description='" + getDescription() + '\'' +
                ", creationDate=" + getCreationDate() +
                ", dueDate=" + getDueDate() +
                ", type=" + getType() +
                '}';
    }
}
