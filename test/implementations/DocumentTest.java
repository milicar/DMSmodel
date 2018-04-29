package implementations;

import model.document.Document;
import model.document.DocumentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DocumentTest {

    private DocumentType dtype;
    private Document doc;
    private Map<String, String> documentDescriptors;

    @Before
    public void setUp() throws Exception {
        dtype = new DocumentType();
        dtype.addDocumentTypeDescriptor("descrName1");
        dtype.addDocumentTypeDescriptor("descrName2");

        documentDescriptors = new HashMap<String, String>() {{
            put("descrName1", "descrValue1");
            put("descrName2", "descrValue2");
        }};

        doc = new Document("", "", dtype);
    }

    @Test
    public void addDocumentDescriptorsTest() {
        doc.addDocumentDescriptors(documentDescriptors);

        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue1"));
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue2"));
    }

    @Test
    public void removeDocumentDescriptorValuesTest() {
        doc.addDocumentDescriptors(documentDescriptors);

        Assert.assertEquals(2, doc.getDocumentDescriptors().size());
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue1"));
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue2"));

        doc.removeDocumentDescriptor("descrName2");

        Assert.assertEquals(2, doc.getDocumentDescriptors().size());
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue1"));
        Assert.assertFalse(doc.getDocumentDescriptors().containsValue("descrValue2"));
    }

    @Test
    public void addDocumentTagsTest() {
        doc.addDocumentTag("ver2");
        doc.addDocumentTag("manual");
        doc.addDocumentTag("current");

        Assert.assertEquals(3, doc.getDocumentTags().size());
        Assert.assertEquals("ver2", doc.getDocumentTags().get(0).getDocumentTagValue());
        Assert.assertEquals("manual", doc.getDocumentTags().get(1).getDocumentTagValue());
        Assert.assertEquals("current", doc.getDocumentTags().get(2).getDocumentTagValue());
    }

    @Test
    public void removeDocumentTagTest() {
        doc.addDocumentTag("ver2");
        doc.addDocumentTag("manual");
        doc.addDocumentTag("current");
        Assert.assertEquals(3, doc.getDocumentTags().size());

        doc.removeDocumentTag("ver2");
        Assert.assertEquals(2, doc.getDocumentTags().size());
        Assert.assertEquals("manual", doc.getDocumentTags().get(0).getDocumentTagValue());
        Assert.assertEquals("current", doc.getDocumentTags().get(1).getDocumentTagValue());




    }

    @Test
    public void ignoreRemovingNonExistingTag()  {
        doc.addDocumentTag("tagValue1");
        Assert.assertEquals(1, doc.getDocumentTags().size());

        doc.removeDocumentTag("non-existing tag");

        Assert.assertEquals(1, doc.getDocumentTags().size());


    }
}
