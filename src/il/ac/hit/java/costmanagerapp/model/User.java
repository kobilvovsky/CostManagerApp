package il.ac.hit.java.costmanagerapp.model;

public class User {
    private int id;
    private String user;
    private String password;

    public User(int id, String user, String password) {
        setId(id);
        setPassword(password);
        setUser(user);
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        if(Integer.toString(id).length() == 9) {
            this.id = id;
        }
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        if (password.length() == 8) {
            this.password = password;
        }
    }
}
