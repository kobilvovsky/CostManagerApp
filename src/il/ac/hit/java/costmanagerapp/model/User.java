/*
    Submitted by:
    Erez Mizrahi 316267087
    Lvovsky Yakov 315825380
    Netanel Tarnorudsky 315424689
 */

package il.ac.hit.java.costmanagerapp.model;

public class User {
    private Username userName;
    private Password userPassword;

    /**
     * User constructor
     * @param userName username object
     * @param userPassword password object
     */
    public User(Username userName, Password userPassword) {
        setUserName(userName);
        setUserPassword(userPassword);
    }

    /**
     * Gets username object - username
     * @return username object
     */
    public Username getUserName() {
        return this.userName;
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
        return this.userPassword;
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
            ", userName=" + userName +
            ", userPassword=" + userPassword +
            '}';
    }
}






