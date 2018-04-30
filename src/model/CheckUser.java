package model;

public interface CheckUser {

    boolean isNotAuthorised(User user);

    boolean isNotLoggedIn(User user);
}
