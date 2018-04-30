package implementations;

import exception.OperationNotAllowedException;
import model.process.hierarchy.Activity;
import model.process.hierarchy.Process;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class ProcessTest {

    private Process process;
    private Process subprocess1;
    private Process subprocess2;
    private Activity activity1;
    private Activity activity2;

    @Before
    public void setUp() {
        process = new Process("mainProcess");
        subprocess1 = new Process("subprocess1");
        subprocess2 = new Process("subprocess2");
        activity1 = new Activity("activity1");
        activity2 = new Activity("activity2");
    }

    @Test
    public void allowAddingSubprocessTest() throws OperationNotAllowedException {
        process.addSubprocess(subprocess1);
        process.addSubprocess(subprocess2);

        Assert.assertEquals(2, process.getSubprocessesList().size());
        Assert.assertTrue(process.getSubprocessesList().containsAll(Arrays.asList(subprocess1, subprocess2)));
        Assert.assertEquals(process.getProcessID(), subprocess1.getParentID());
        Assert.assertEquals(process.getProcessID(), subprocess2.getParentID());

    }

    @Test(expected = OperationNotAllowedException.class)
    public void denyAddingSubprocessToPrimitiveProcessTest() throws OperationNotAllowedException {
        process.addActivity(activity1);
        process.addSubprocess(subprocess1);
    }

    @Test
    public void removeSubprocessTest() throws OperationNotAllowedException {
        process.addSubprocess(subprocess1);
        process.removeSubprocess(subprocess1);

        Assert.assertEquals(0, process.getSubprocessesList().size());
    }

    @Test
    public void allowAddingActivityTest() throws OperationNotAllowedException {
        process.addActivity(activity1);

        Assert.assertEquals(1, process.getActivitiesList().size());
    }

    @Test(expected = OperationNotAllowedException.class)
    public void denyAddingActivityToComplexProcessTest() throws OperationNotAllowedException {
        process.addSubprocess(subprocess1);
        process.addActivity(activity1);
    }

    @Test
    public void removeActivityTest() throws OperationNotAllowedException {
        process.addActivity(activity1);
        process.addActivity(activity2);

        process.removeActivity(activity2);

        Assert.assertEquals(1, process.getActivitiesList().size());
    }
}
