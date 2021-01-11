package il.ac.hit.java.costmanagerapp.model;

public class User {
    private int userId;
    private Username userName;
    private Password userPassword;

    /**
     * User constructor
     * @param userName username object
     * @param userPassword password object
     */
    public User(Username userName, Password userPassword) {
        //setId(userId);
        setUserName(userName);
        setUserPassword(userPassword);
    }

//    public int getId() {
//        return userId;
//    }
//
//    public void setId(int userId) {
//        this.userId = userId;
//    }

    /**
     * Gets username object
     * @return username object
     */
    public Username getUserName() {
        return userName;
    }

    /**
     * Sets username object
     * @param userName username object
     */
    public void setUserName(Username userName) {
        this.userName = userName;
    }

    /**
     * Gets password object
     * @return password object
     */
    public Password getUserPassword() {
        return userPassword;
    }

    /**
     * Sets password object
     * @param userPassword
     */
    public void setUserPassword(Password userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Prints user variables data
     * @return user object as string
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName=" + userName +
                ", userPassword=" + userPassword +
                '}';
    }
}






