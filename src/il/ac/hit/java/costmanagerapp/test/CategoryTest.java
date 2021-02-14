package il.ac.hit.java.costmanagerapp.test;

import il.ac.hit.java.costmanagerapp.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getCategoryName() {
        Category c=new Category("Food");
        assertEquals("Food",c.getCategoryName());
    }

    @Test
    void setCategoryName() {
        Category c=new Category("Food");
        c.setCategoryName("School");
        assertEquals("School",c.getCategoryName());
    }

    @Test
    void isValidCategory() {
        Category c=new Category("Food");
        assertTrue(c.isValidCategory(c.getCategoryName()));
    }

    @Test
    void testEquals() {
        Category c =new Category("Food");
        Category temp = new Category("Food");
        assertTrue(c.equals(temp));
    }
}