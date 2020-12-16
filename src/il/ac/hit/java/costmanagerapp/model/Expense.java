package il.ac.hit.java.costmanagerapp.model;
import il.ac.hit.java.costmanagerapp.model.Category;
import il.ac.hit.java.costmanagerapp.model.Currency;
import il.ac.hit.java.costmanagerapp.model.Frequency;

import java.time.LocalTime;
import java.util.Date;

public class Expense {
    private int id;
    private String name;
    private int cost; // cost of expense
    private Category category;
    private int sum; // total of expense over period of time (monthly, yearly)
    private Currency currency;
    private String description;
    private Date date;
    private LocalTime time;
    private Frequency type;

    public Expense(int id, String name, int cost, Category category, int sum, Currency currency, String description, Date date, LocalTime time, Frequency type) {
        setId(id);
        setName(name);
        setCost(cost);
        setCategory(category);
        setSum(sum);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
        setTime(time);
        setType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Frequency getType() {
        return type;
    }

    public void setType(Frequency type) {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", category=" + category +
                ", sum=" + sum +
                ", currency=" + currency +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", type=" + type +
                '}';
    }
}
