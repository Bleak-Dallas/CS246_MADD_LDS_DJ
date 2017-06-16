package edu.byui.maddldsdj;


public class User {
    private String userEmail;
    private String userID;
    private boolean isAdmin;

    // default constructor
    public User() {
    }

    // non-default constructor
    public User(String email, String uid) {
        this.userEmail = email;
        this.userID = uid;
        this.isAdmin = false;
    }

    // non-default constructor with admin
    public User(String email, String uid, boolean admin) {
        this.userEmail = email;
        this.userID = uid;
        this.isAdmin = admin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
