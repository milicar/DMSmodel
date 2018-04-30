package model;


public class User {

    private long userID;
    private String firstName;
    private String lastName;
    private long companyID;
    private String username;
    private String password;
    private String userEmail;
    private Role role;

    public String getEmail() {
        return null;
    }

    public enum Role {ADMIN, EMPLOYEE}

    public User(Role role) {
        this("emptyUser", "", -1L, "", "", role);
    }

    public User(String firstName, String lastName, long companyID, String username, String password, Role role) {
        this.userID = generateuserID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyID = companyID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private long generateuserID() {
        return 3L;
    }

    public long getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getCompanyID() {
        return companyID;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
