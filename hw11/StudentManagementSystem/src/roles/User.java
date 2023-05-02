/**
 * Class User
 * 
 * @author Lihong Zhao & Haojie Zheng
 */


package roles;

import file.FileInfoReader;

/**
 * Represent a user
 */
public abstract class User {

    /**
     * user id
     */
    String id;
    /**
     * name of user
     */
    String name;
    /**
     * username
     */
    String username;
    /**
     * password
     */
    String password;

    /**
     * login in method
     * @param username
     * @param password
     * @param file
     * @return true if login in successfully
     */
    protected abstract boolean login(String username, String password, FileInfoReader file);

    /**
     * get user information after login in
     * @param username
     * @param password
     * @param file
     * @return user information
     */
    protected abstract User getLogin(String username, String password, FileInfoReader file);

    
    public User tryLogin(String username, String password, FileInfoReader file) {
        if (login(username, password, file)) {
            return getLogin(username, password, file);
        }
        return null;
    }
    
    
    /**
     * @return user id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return user name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return this.username;
    }
}
