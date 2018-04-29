import exception.UnauthorisedUserException;
import model.User;
import model.document.Document;
import model.document.DocumentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.DocumentService;
import service.DocumentTypeService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class DocumentServiceTest {

    private User admin;
    private User employee;
    private DocumentTypeService dts;
    private DocumentType dtype;
    private DocumentService ds;


    @Before
    public void setUp() throws UnauthorisedUserException {
        admin = new User();
        admin.setRole(User.Role.ADMIN);

        employee = new User();
        employee.setRole(User.Role.EMPLOYEE);

        dts = new DocumentTypeService();
        ds = new DocumentService();

        dtype = dts.createDocumentType(admin, 1L,
                "type1", "C:", "", Collections.singletonList("descriptor1name"));
    }


    @Test
    public void allowAdminToCreateDocumentTest() throws UnauthorisedUserException {

        Map<String, String> descriptorMap = new HashMap<String, String>(){{put("descriptor1name", "descriptor1value");}};
        Document document = new Document("doc1", "C:", dtype);
        document.addDocumentDescriptors(descriptorMap);


        Assert.assertEquals(ds.createDocument(admin, "doc1",
                "C:", dtype, descriptorMap), document);
    }

    @Test
    public void allowEmployeeToCreateDocumentTest() throws UnauthorisedUserException{

        Document document = ds.createDocument(employee, "doc1",
                "C:", dtype, Collections.EMPTY_MAP);

        Assert.assertEquals(new Document("doc1", "C:", dtype), document);
    }

//    @Test
//    public void allowEmployeeToAddDocumentDescriptorsTest() throws UnauthorisedUserException{
//
//        Document document = ds.createDocument(employee, "doc1",
//                "C:", dtype, Collections.EMPTY_MAP);
//        DocumentType.Descriptor documentTypeDescriptor1 =
//                dtype.getDocumentTypeDescriptors().get(0);
//        ds.addDocumentDescriptor(employee, document,
//             documentTypeDescriptor1, "descriptor1value");
//
//        Assert.assertEquals("descriptor1value", document.getDocumentDescriptors().get(documentTypeDescriptor1));
//    }
}
