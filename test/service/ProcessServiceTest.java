package service;

import exception.OperationNotAllowedException;
import exception.UnauthorisedUserException;
import model.User;
import model.process.hierarchy.Activity;
import model.process.hierarchy.Company;
import model.process.hierarchy.Process;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ProcessService;

public class ProcessServiceTest {

    private ProcessService processService;
    private User admin;
    private User employee;
    private Company company;
    private Process primitiveProcess;
    private Process complexProcess;
    private Process processWithoutChildren;

    @Before
    public void setUp() throws OperationNotAllowedException {
        processService = new ProcessService();
        admin = new User();
        admin.setRole(User.Role.ADMIN);
        employee = new User();
        employee.setRole(User.Role.EMPLOYEE);
        company = new Company("company");
        processWithoutChildren = new Process("processWithoutChildren");

        primitiveProcess = new Process("primitiveProcess");
        primitiveProcess.addActivity(new Activity());
        Assert.assertTrue(primitiveProcess.isPrimitive());

        complexProcess = new Process("complexProcess");
        complexProcess.addSubprocess(new Process("firstSubprocess"));
        Assert.assertTrue(complexProcess.isComplex());
    }

    @Test
    public void allowAdminToCreateCompanysProcessTest() throws OperationNotAllowedException, UnauthorisedUserException {

        Process created = processService.createProcess(admin, company, "processCreated", "");

        Assert.assertNotNull(created);
        Assert.assertTrue(company.getProcessList().contains(created));
    }

    @Test
    public void allowCreatingSubprocessOfAProcessWithoutChildrenTest() throws OperationNotAllowedException, UnauthorisedUserException {

        Process subprocess = processService.createProcess(admin, processWithoutChildren, "subprocess", "");

        Assert.assertNotNull(subprocess);
        Assert.assertTrue(processWithoutChildren.getSubprocessesList().contains(subprocess));
    }

    @Test
    public void allowCreatingSubprocessOfAComplexProcess() throws OperationNotAllowedException, UnauthorisedUserException {

        Process createdSubprocess = processService.createProcess(admin, complexProcess, "createdSubprocess", "");

        Assert.assertEquals(2, complexProcess.getSubprocessesList().size());
        Assert.assertTrue(complexProcess.getSubprocessesList().contains(createdSubprocess));


    }

    @Test(expected = OperationNotAllowedException.class)
    public void denyCreatingSubprocessOfAPrimitiveProcess() throws OperationNotAllowedException, UnauthorisedUserException {

        Process notCreatedSubprocess = processService.createProcess(admin, primitiveProcess, "notCreatedSubprocess", "");
    }

    @Test(expected = UnauthorisedUserException.class)
    public void denyEmployeeCreatingProcess() throws OperationNotAllowedException, UnauthorisedUserException {
        Process notCreated = processService.createProcess(employee, company, "notCreated", "");
    }
}
