package service;

import exception.UnauthorisedUserException;
import model.BusinessContact;
import model.User;
import model.process.hierarchy.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyServiceTest {

    private User admin;
    private User employee;
    private CompanyService companyService;
    private BusinessContactService businessContactService;

    @Before
    public void setUp() {
        admin = new User();
        admin.setRole(User.Role.ADMIN);
        employee = new User();
        employee.setRole(User.Role.EMPLOYEE);
        companyService = new CompanyService();
        businessContactService = new BusinessContactService();

    }

    @Test
    public void allowAdminToCreateCompanyTest() throws UnauthorisedUserException {
        Company createdCompany = companyService.createCompany(admin, "company", "", "");

        Assert.assertNotNull(createdCompany);
    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeCreatingCompanyTest() throws UnauthorisedUserException {
        companyService.createCompany(employee, "company", "", "");

    }

    @Test
    public void allowAdminToAddCompanyContactsTest() throws UnauthorisedUserException {
        Company createdCompany = companyService.createCompany(admin, "company", "", "");
        BusinessContact businessContact = businessContactService.createBusinessContact(admin, "", "", "", "");

        companyService.addCompanyContact(admin, createdCompany, businessContact);

        Assert.assertTrue(createdCompany.getBusinessContacts().contains(businessContact));

    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeAddingCompanyContactTest() throws UnauthorisedUserException {
        Company createdCompany = companyService.createCompany(admin, "company", "", "");
        BusinessContact businessContact = businessContactService.createBusinessContact(admin, "", "", "", "");

        companyService.addCompanyContact(employee, createdCompany, businessContact);
    }
}
