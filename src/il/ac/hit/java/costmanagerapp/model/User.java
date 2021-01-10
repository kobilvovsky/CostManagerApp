package il.ac.hit.java.costmanagerapp.model;

public class User {
    private int userId;
    private Username userName;
    private Password userPassword;

    //User instance will be crated after retrieving the new id from the DB
    public User(Username userName, Password userPassword) {
//        setId(userId);
        setUserName(userName);
        setUserPassword(userPassword);
    }

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public Username getUserName() {
        return userName;
    }

    public void setUserName(Username userName) {
        this.userName = userName;
    }

    public Password getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(Password userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName=" + userName +
                ", userPassword=" + userPassword +
                '}';
    }
}






