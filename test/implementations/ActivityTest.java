package implementations;

import model.process.hierarchy.Activity;
import model.document.DocumentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ActivityTest {

    private Activity activity;
    private DocumentType dtype1;
    private DocumentType dtype2;
    private DocumentType dtype3;

    @Before
    public void setUp() throws Exception {
        activity = new Activity();
        dtype1 = new DocumentType("type1");
        dtype2 = new DocumentType("type2");
        dtype3 = new DocumentType("type3");
    }

    @Test
    public void addActivityInAndOutDocuments() {

        activity.addActivityDocumentTypes(Activity.Direction.IN, Collections.singletonList(dtype1));
        activity.addActivityDocumentTypes(Activity.Direction.OUT, Arrays.asList(dtype2, dtype3));

        Assert.assertEquals(1, activity.getActivityDocumentTypes().get(Activity.Direction.IN).size());
        Assert.assertEquals(2, activity.getActivityDocumentTypes().get(Activity.Direction.OUT).size());
        Assert.assertEquals(new DocumentType("type1"), activity.getActivityDocumentTypes().get(Activity.Direction.IN).get(0));
        Assert.assertEquals(new DocumentType("type2"), activity.getActivityDocumentTypes().get(Activity.Direction.OUT).get(0));
        Assert.assertEquals(new DocumentType("type3"), activity.getActivityDocumentTypes().get(Activity.Direction.OUT).get(1));
    }

    @Test
    public void removeActivityInAndOutDocuments() {

        activity.addActivityDocumentType(Activity.Direction.IN, dtype1);
        activity.addActivityDocumentType(Activity.Direction.OUT, dtype2);
        activity.addActivityDocumentType(Activity.Direction.OUT, dtype3);

        Assert.assertEquals(1, activity.getActivityDocumentTypes().get(Activity.Direction.IN).size());
        Assert.assertEquals(2, activity.getActivityDocumentTypes().get(Activity.Direction.OUT).size());

        activity.removeActivityDocumentType(Activity.Direction.IN, dtype1);
        activity.removeActivityDocumentType(Activity.Direction.OUT, dtype2);

        Assert.assertEquals(0, activity.getActivityDocumentTypes().get(Activity.Direction.IN).size());
        Assert.assertEquals(1, activity.getActivityDocumentTypes().get(Activity.Direction.OUT).size());
        Assert.assertEquals(new DocumentType("type3"), activity.getActivityDocumentTypes().get(Activity.Direction.OUT).get(0));
    }


    @Test
    public void ignoreRemovingNonExistingDocumentTypes() {
        activity.addActivityDocumentType(Activity.Direction.IN, dtype1);
        Assert.assertEquals(1, activity.getActivityDocumentTypes().get(Activity.Direction.IN).size());
        Assert.assertEquals(0, activity.getActivityDocumentTypes().get(Activity.Direction.OUT).size());

        activity.removeActivityDocumentType(Activity.Direction.IN, dtype2);
        Assert.assertEquals(1, activity.getActivityDocumentTypes().get(Activity.Direction.IN).size());

        activity.removeActivityDocumentType(Activity.Direction.OUT, dtype2);
        Assert.assertEquals(0, activity.getActivityDocumentTypes().get(Activity.Direction.OUT).size());
    }
}
