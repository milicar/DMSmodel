package service;

import exception.UnauthorisedUserException;
import model.BusinessContact;
import model.CheckUser;
import model.User;
import model.process.hierarchy.Company;

public class CompanyService implements CheckUser {

    public Company createCompany(User user, String companyName, String companyAddress, String companyDescription) throws UnauthorisedUserException {
        if (isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();

        return new Company(companyName, companyAddress, companyDescription);
    }

    @Override
    public boolean isNotAuthorised(User user) {
        return user.getRole() != User.Role.ADMIN;
    }

    @Override
    public boolean isNotLoggedIn(User user) {
        return false;
    }

    public void addCompanyContact(User user, Company company, BusinessContact businessContact) throws UnauthorisedUserException {
        if(isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();

        company.addBusinessContact(businessContact);
    }
}
