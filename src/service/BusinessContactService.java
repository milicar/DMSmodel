package service;

import exception.UnauthorisedUserException;
import model.BusinessContact;
import model.CheckUser;
import model.User;

public class BusinessContactService implements CheckUser {

    public BusinessContactService() {
    }

    public BusinessContact createBusinessContact(User user, String contactName, String contactInformation,
                                                 String contactAddress, String contactEmail) throws UnauthorisedUserException {
        if(isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();

        return new BusinessContact(contactName, contactInformation, contactAddress, contactEmail);
    }

    @Override
    public boolean isNotAuthorised(User user) {
        return user.getRole() != User.Role.ADMIN;
    }

    @Override
    public boolean isNotLoggedIn(User user) {
        return false;
    }
}
