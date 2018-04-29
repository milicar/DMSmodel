import exception.UnauthorisedUserException;
import model.document.Document;
import model.document.DocumentType;
import model.process.hierarchy.Activity;
import model.User;
import model.process.hierarchy.Process;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ActivityService;

import java.util.Arrays;

public class ActivityServiceTest {

    private User employee;
    private User admin;
    private Process parentProcess;
    private ActivityService activityService;
    private Activity createdActivity;

    @Before
    public void setUp() throws Exception {
        employee = new User();
        employee.setRole(User.Role.EMPLOYEE);

        admin = new User();
        admin.setRole(User.Role.ADMIN);

        parentProcess = new Process();

        activityService = new ActivityService();
    }

    @Test
    public void allowAdminToCreateActivity() throws UnauthorisedUserException {
        createdActivity = activityService.createActivity(admin, "activityName1", "activityDescription1", parentProcess);

        Assert.assertEquals("activityName1", createdActivity.getActivityName());
        Assert.assertEquals("activityDescription1", createdActivity.getActivityDescription());
    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeCreatingActivity() throws UnauthorisedUserException {
        Activity notCreatedActivity = activityService.createActivity(employee, "name1", "description1", parentProcess);
    }

    @Test
    public void allowAdminToAddDocumentType() throws UnauthorisedUserException {
        createdActivity = activityService.createActivity(admin, "activityName1", "activityDescription1", parentProcess);
        DocumentType documentType = new DocumentType("doc1");

        activityService.addDocumentType(admin, createdActivity, Activity.Direction.IN, documentType);

        Assert.assertEquals(1, createdActivity.getActivityDocumentTypes().get(Activity.Direction.IN).size());
        Assert.assertEquals("doc1", createdActivity.getActivityDocumentTypes().get(Activity.Direction.IN).get(0).getDocumentTypeName());
        //what a train wreck!!! But makes me calm about documentType added
    }

    @Test
    public void allowAdminToAddDocumentTypeList() throws UnauthorisedUserException {
        createdActivity = activityService.createActivity(admin, "activityName1", "activityDescription1", parentProcess);
        DocumentType docType1 = new DocumentType("doc1");
        DocumentType docType2 = new DocumentType("doc2");


        activityService.addDocumentTypes(admin, createdActivity, Activity.Direction.OUT, Arrays.asList(docType1, docType2));

        Assert.assertEquals(2, activityService.getActivityDocumentTypeList(admin, createdActivity, Activity.Direction.OUT).size());
    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeAddingDocumentType() throws UnauthorisedUserException {
        createdActivity = activityService.createActivity(admin, "activityName1", "activityDescription1", parentProcess);
        DocumentType documentType = new DocumentType("doc1");

        activityService.addDocumentType(employee, createdActivity, Activity.Direction.IN, documentType);
    }


}
