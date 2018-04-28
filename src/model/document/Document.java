package model.document;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Document {

    private long documentID;
    private String documentName;
    private Path documentLocation;
    private DocumentType documentType;
    private Map<DocumentType.DocumentTypeDescriptor, String> documentDescriptors;
    private List<DocumentTag> documentTags;

    public Document() {
        this.documentID = generateID();
        this.documentName = "";
        this.documentLocation = Paths.get("");
        this.documentType = new DocumentType();
        this.documentDescriptors = Collections.emptyMap();
    }

    public Document(String documentName, String documentLocation, DocumentType documentType) {
        this.documentID = generateID();
        this.documentName = documentName;
        this.documentLocation = Paths.get(documentLocation);
        this.documentType = documentType;
        this.documentDescriptors = giveDefaultDescriptorValues(documentType);
        // initialize tag list??
    }

    private Map<DocumentType.DocumentTypeDescriptor, String> giveDefaultDescriptorValues(DocumentType dt) {
        Map<DocumentType.DocumentTypeDescriptor, String> descriptors = new HashMap<>();
        for (DocumentType.DocumentTypeDescriptor dtd : dt.getDocumentTypeDescriptors()) {
            descriptors.put(dtd, "");
        }
        return descriptors;
    }

    private long generateID() {
        return 0L;
    }

    public long getDocumentID() {
        return documentID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Path getDocumentLocation() {
        return documentLocation;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public Map<DocumentType.DocumentTypeDescriptor, String> getDocumentDescriptors() {
        return (documentDescriptors != null) ? documentDescriptors : new HashMap<>();
    }

    public void addDocumentDescriptors(Map<String, String> documentDescriptors){
        for(String key : documentDescriptors.keySet()){
            DocumentType.DocumentTypeDescriptor descriptor = getDocumentType().findDocumentTypeDescriptor(key);
            addDocumentDescriptorValue(descriptor, documentDescriptors.get(key));
        }
    }

    private void addDocumentDescriptorValue(DocumentType.DocumentTypeDescriptor descriptorName, String descriptorValue) {
        documentDescriptors = getDocumentDescriptors();
        if (documentDescriptors.containsKey(descriptorName))
            documentDescriptors.put(descriptorName, descriptorValue);
    }

    // actually, remove shouldn't remove, only reset to empty string ("")
    /*
    public void removeDocumentDescriptor(DocumentType.DocumentTypeDescriptor descriptorName){
        documentDescriptors = getDocumentDescriptors();

        if (documentDescriptors.containsKey(descriptorName))
                documentDescriptors.remove(descriptorName);
        }
    */

    public void removeDocumentDescriptor(String descriptorName){
        DocumentType.DocumentTypeDescriptor descriptor = getDocumentType().findDocumentTypeDescriptor(descriptorName);
        removeDocumentDescriptor(descriptor);
    }

    public void removeDocumentDescriptor(DocumentType.DocumentTypeDescriptor descriptorName) {
        documentDescriptors = getDocumentDescriptors();

        if (documentDescriptors.containsKey(descriptorName))
            documentDescriptors.replace(descriptorName, "");
    }

    public List<DocumentTag> getDocumentTags() {
        return (documentTags != null) ? documentTags : new ArrayList<>();
    }

    public void addDocumentTag(String tag) {
        documentTags = getDocumentTags();
        documentTags.add(new DocumentTag(tag));
    }

    public void removeDocumentTag(String tagValue) {
        for (DocumentTag tag : documentTags) {
            if (tag.getDocumentTagValue().equals(tagValue))
                documentTags.remove(tag);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Document document = (Document) other;
        return Objects.equals(getDocumentID(), document.getDocumentID()) &&
                Objects.equals(getDocumentName(), document.getDocumentName()) &&
                Objects.equals(getDocumentLocation(), document.getDocumentLocation()) &&
                Objects.equals(getDocumentType(), document.getDocumentType()) &&
                Objects.equals(getDocumentDescriptors(), document.getDocumentDescriptors());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDocumentID(), getDocumentName(), getDocumentLocation(), getDocumentType(), getDocumentDescriptors());
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentID='" + documentID + '\'' +
                ", documentName='" + documentName + '\'' +
                ", documentLocation=" + documentLocation +
                ", documentType=" + documentType +
                ", documentDescriptors=" + getDocumentDescriptors() +
                ", documentTags=" + getDocumentTags() +
                '}';
    }


    public class DocumentTag {
        private String documentTagID;
        private String documentTagValue;

        public DocumentTag() {
        }

        public DocumentTag(String documentTagValue) {
            this.documentTagValue = documentTagValue;
        }

        public String getDocumentTagValue() {
            return documentTagValue;
        }

        @Override
        public String toString() {
            return "documentTagValue='" + documentTagValue + '\'';
        }
    }
}
