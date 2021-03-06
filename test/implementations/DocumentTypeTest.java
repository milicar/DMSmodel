package implementations;

import model.document.DocumentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DocumentTypeTest {

    @Test
    public void addDocumentTypeDescriptorsTest() {
        DocumentType dt = new DocumentType();
        List<DocumentType.Descriptor> descriptors = dt.getDocumentTypeDescriptors();

        Assert.assertNotNull(descriptors);

        dt.addDocumentTypeDescriptor("descriptor1");
        dt.addDocumentTypeDescriptor("descriptor2");

        Assert.assertEquals(2, dt.getDocumentTypeDescriptors().size());
        Assert.assertEquals("descriptor1", dt.getDocumentTypeDescriptors().get(0).getDocumentTypeDescriptorName());
        Assert.assertEquals("descriptor2", dt.getDocumentTypeDescriptors().get(1).getDocumentTypeDescriptorName());

    }

    @Test
    public void removeDocumentTypeDescriptorTest() {
        DocumentType dt = new DocumentType("docname1",
                "location/path", "description");
        dt.addDocumentTypeDescriptor("descriptor1");
        dt.addDocumentTypeDescriptor("descriptor2");
        Assert.assertEquals(2, dt.getDocumentTypeDescriptors().size());

        dt.removeDocumentTypeDescriptor("descriptor1");

        Assert.assertEquals(1, dt.getDocumentTypeDescriptors().size());
        Assert.assertNotEquals("descriptor1", dt.getDocumentTypeDescriptors().get(0).getDocumentTypeDescriptorName());
    }
}
