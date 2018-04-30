package service;

import exception.UnauthorisedUserException;
import model.User;
import model.document.DocumentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.DocumentTypeService;

import java.util.Arrays;
import java.util.Collections;


public class DocumentTypeServiceTest {

    private User employee;
    private User admin;
    private DocumentTypeService dtService;

    @Before
    public void setUp() {
        employee = new User();
        employee.setRole(User.Role.EMPLOYEE);

        admin = new User();
        admin.setRole(User.Role.ADMIN);

        dtService = new DocumentTypeService();
    }

    @Test
    public void allowAdminToCreateBareDocumentTypeTest() throws UnauthorisedUserException {

        DocumentType documentTypeWithoutDescriptors = dtService.createDocumentType(admin,
                1L, "type1",
                "C:", "desc", Collections.emptyList());

        Assert.assertEquals(new DocumentType("type1",
                "C:", "desc"), documentTypeWithoutDescriptors);

    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeCreatingDocumetTypeTest() throws UnauthorisedUserException {

        DocumentType dt = dtService.createDocumentType(employee, "type1");

    }

    @Test
    public void allowAdminToCreateDocumentTypeWithDescriptorsTest() throws UnauthorisedUserException {

        DocumentType dt = dtService.createDocumentType(admin,
                1L, "type1",
                "C:", "description", Arrays.asList("typeDescriptor1", "typeDescriptor2"));

        Assert.assertEquals(2, dt.getDocumentTypeDescriptors().size());
        Assert.assertEquals("typeDescriptor1", dt.getDocumentTypeDescriptors().get(0).getDocumentTypeDescriptorName());
        Assert.assertEquals("typeDescriptor2", dt.getDocumentTypeDescriptors().get(1).getDocumentTypeDescriptorName());
    }
}
