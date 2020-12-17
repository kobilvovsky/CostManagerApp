package il.ac.hit.java.costmanagerapp.tests;

import il.ac.hit.java.costmanagerapp.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User u1 = new User(123456789,"Nati","@abc123");

    @Test
    void getId() {
        User u1 = new User(123456789,"Nati","@abc123");
        assertEquals(123456789,u1.getId());
    }

    @Test
    void getPassword() {
        User u1 = new User(123456789,"Nati","123123");
        System.out.println(u1.getPassword());
        int pas1 = Integer.parseInt(u1.getPassword());
        System.out.println(pas1);
        assertEquals(123123,pas1);
    }

    @Test
    void setId(){


    }
}