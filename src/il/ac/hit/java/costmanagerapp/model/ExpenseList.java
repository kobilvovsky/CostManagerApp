package il.ac.hit.java.costmanagerapp.model;
import il.ac.hit.java.costmanagerapp.model.Expense;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExpenseList {
    private List<Expense> list = new ArrayList<Expense>();

    public ExpenseList() {

    }

    public List<Expense> getList() {
        return list;
    }

    public Expense retrieve(String name) {
        Iterator<Expense> iterator = list.iterator();

        while (iterator.hasNext()) {
            Expense ex = iterator.next();
            if (ex.getName().equals(name)) {
                return ex;
            }
        }
        return null;
    }

    public void addExpense(Expense ex) { list.add(ex); }
    public void removeExpense(Expense ex) {
        list.remove(ex);
    }
    public void editExpense(Expense ex, Expense newEx) {
        ex = newEx;
    }

    // cost/date/category
    public void sortList() {
        //Collections.sort(list);
    }

    public void printList() {
        for(Expense ex:list)
            System.out.println(ex.toString());
    }
}
