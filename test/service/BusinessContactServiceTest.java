package service;

import exception.UnauthorisedUserException;
import model.BusinessContact;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusinessContactServiceTest {

    private BusinessContactService businessContactService;
    private User admin;
    private User employee;

    @Before
    public void setUp() {
        businessContactService = new BusinessContactService();
        admin = new User();
        admin.setRole(User.Role.ADMIN);
        employee = new User();
        employee.setRole(User.Role.EMPLOYEE);
    }

    @Test
    public void allowAdminToCreateBusinessContact() throws UnauthorisedUserException {

        BusinessContact businessContact = businessContactService.createBusinessContact(admin,
                "contact", "", "", "");

        Assert.assertNotNull(businessContact);
    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeCreatingBusinessContact() throws UnauthorisedUserException {

        businessContactService.createBusinessContact(employee,
                "contact", "", "", "");
    }
}
