package implementations;

import model.document.Document;
import model.document.DocumentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DocumentTest {

  //  private DocumentType.Descriptor descrName1;
   // private DocumentType.Descriptor descrName2;
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
    public void addDocumentDescriptors() {

        doc.addDocumentDescriptors(documentDescriptors);

        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue1"));
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue2"));

    }

    @Test
    public void removeDocumentDescriptorValues() {

        doc.addDocumentDescriptors(documentDescriptors);


        Assert.assertEquals(2, doc.getDocumentDescriptors().size());
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue1"));
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue2"));

        doc.removeDocumentDescriptor("descrName2");

        Assert.assertEquals(2, doc.getDocumentDescriptors().size());
        Assert.assertTrue(doc.getDocumentDescriptors().containsValue("descrValue1"));
        Assert.assertFalse(doc.getDocumentDescriptors().containsValue("descrValue2"));
    }
}
