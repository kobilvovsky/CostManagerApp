/*
    Submitted by:
    Erez Mizrahi 316267087
    Lvovsky Yakov 315825380
    Netanel Tarnorudsky 315424689
 */

package il.ac.hit.java.costmanagerapp.test;

import il.ac.hit.java.costmanagerapp.model.Currency;
import il.ac.hit.java.costmanagerapp.model.Expense;
import il.ac.hit.java.costmanagerapp.model.Frequency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void getCost() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        assertEquals(5.5,e.getCost());
    }

    @Test
    void setCost() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        e.setCost(10.2);
        assertEquals(10.2,e.getCost());
    }

    @Test
    void getCategory() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        assertEquals("Food",e.getCategory());
    }

    @Test
    void setCategory() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        e.setCategory("School");
        assertEquals("School",e.getCategory());
    }

    @Test
    void getCurrency() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        assertEquals(Currency.USD,e.getCurrency());
    }

    @Test
    void setCurrency() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        e.setCurrency(Currency.EURO);
        assertEquals(Currency.EURO,e.getCurrency());
    }

    @Test
    void getDescription() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        assertEquals("EREZ",e.getDescription());
    }

    @Test
    void setDescription() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        e.setDescription("HELLO");
        assertEquals("HELLO",e.getDescription());
    }

    @Test
    void getCreationDate() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
        assertEquals(date,e.getCreationDate());
    }

    @Test
    void getDueDate() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        assertEquals("2021-12-12",e.getDueDate());
    }

    @Test
    void setDueDate() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        e.setDueDate("2022-12-12");
        assertEquals("2022-12-12",e.getDueDate());
    }

    @Test
    void getType() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        assertEquals(Frequency.YEARLY,e.getType());
    }

    @Test
    void setType() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        e.setType(Frequency.YEARLY.MONTHLY);
        assertEquals(Frequency.MONTHLY,e.getType());
    }

    @Test
    void testToString() {
        Expense e = new Expense(5.5,"Food", Currency.USD,"EREZ","2021-12-12", Frequency.YEARLY);
        String expected="Expense{cost=5.5, category=Food, currency=USD, description='EREZ', creationDate=2021-02-13, dueDate=2021-12-12, type=YEARLY}";
        assertEquals(expected,e.toString());

    }
}