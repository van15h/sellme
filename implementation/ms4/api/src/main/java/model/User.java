package model;

import com.google.gson.annotations.Expose;

/**
 * Class to create advertisement objects. Each user can have one or more of these objects.
 * They all will have unique id.
 */
public class User {

    private String name;

    private String password;
    private String email;
    User() {

    }

    /**
     * Constructor for Advertisement objects
     * @param email user id, who created the advertisement
     * @param password main tags/naming of the advertisement
     * @param name price of advertisement
     */
    public User(String email, String password, String name) {
        this();
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
