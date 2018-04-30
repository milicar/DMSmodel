package service;

import exception.UnauthorisedUserException;
import model.User;
import model.process.hierarchy.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyServiceTest {

    private User admin;
    private User employee;
    private CompanyService companyService;

    @Before
    public void setUp() {
        admin = new User();
        admin.setRole(User.Role.ADMIN);
        employee = new User();
        employee.setRole(User.Role.EMPLOYEE);
        companyService = new CompanyService();

    }

    @Test
    public void allowAdminToCreateCompanyTest() throws UnauthorisedUserException {
        Company createdCompany = companyService.createCompany(admin, "company", "", "");

        Assert.assertNotNull(createdCompany);
    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeCreatingCompanyTest() throws UnauthorisedUserException {
        Company createdCompany = companyService.createCompany(employee, "company", "", "");

    }
}
