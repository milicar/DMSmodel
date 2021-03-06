package model.document;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Document {

    private long documentID;
    private String documentName;
    private Path documentLocation;
    private DocumentType documentType;
    private Map<DocumentType.Descriptor, String> documentDescriptors;
    private List<DocumentTag> documentTags;

    public Document() {
        this("", "", new DocumentType());
    }

    public Document(String documentName, String documentLocation, DocumentType documentType) {
        this.documentID = generateID();
        this.documentName = documentName;
        this.documentLocation = Paths.get(documentLocation);
        this.documentType = documentType;
        this.documentDescriptors = giveDefaultDescriptorValues(documentType);
    }

    private Map<DocumentType.Descriptor, String> giveDefaultDescriptorValues(DocumentType dt) {
        Map<DocumentType.Descriptor, String> descriptors = new HashMap<>();
        for (DocumentType.Descriptor dtd : dt.getDocumentTypeDescriptors()) {
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

    public Map<DocumentType.Descriptor, String> getDocumentDescriptors() {
        return (documentDescriptors != null) ? documentDescriptors : new HashMap<>();
    }

    public void addDocumentDescriptorValues(Map<String, String> documentDescriptors) {
        for (String key : documentDescriptors.keySet()) {
            DocumentType.Descriptor descriptor = getDocumentType().findDocumentTypeDescriptor(key);
            addDocumentDescriptorValue(descriptor, documentDescriptors.get(key));
        }
    }

    private void addDocumentDescriptorValue(DocumentType.Descriptor descriptorName, String descriptorValue) {
        documentDescriptors = getDocumentDescriptors();
        if (documentDescriptors.containsKey(descriptorName))
            documentDescriptors.put(descriptorName, descriptorValue);
    }

    // actually, remove shouldn't remove, only reset to empty string ("")
    /*
    public void removeDocumentDescriptor(DocumentType.Descriptor descriptorName){
        documentDescriptors = getDocumentDescriptors();

        if (documentDescriptors.containsKey(descriptorName))
                documentDescriptors.remove(descriptorName);
        }
    */

    public void removeDocumentDescriptor(String descriptorName) {
        DocumentType.Descriptor descriptor = getDocumentType().findDocumentTypeDescriptor(descriptorName);
        removeDocumentDescriptor(descriptor);
    }

    private void removeDocumentDescriptor(DocumentType.Descriptor descriptorName) {
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

    public DocumentTag findDocumentTagByValue(String tagValue) {
        for (DocumentTag documentTag : getDocumentTags()) {
            if (tagValue.equals(documentTag.documentTagValue))
                return documentTag;
        }
        return null;
    }

    public void removeDocumentTag(String tagValue) {
        documentTags = getDocumentTags();
        DocumentTag tag = findDocumentTagByValue(tagValue);

        documentTags.remove(tag);
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
        private long documentTagID;
        private String documentTagValue;

        public DocumentTag() {
            this.documentTagID = generateTagID();
            this.documentTagValue = "";
        }

        public DocumentTag(String documentTagValue) {
            this.documentTagID = generateTagID();
            this.documentTagValue = documentTagValue;
        }

        private long generateTagID() {
            return 7L;
        }

        public String getDocumentTagValue() {
            return documentTagValue;
        }


        @Override
        public String toString() {
            return "documentTagValue='" + documentTagValue + '\'';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DocumentTag that = (DocumentTag) o;
            return documentTagID == that.documentTagID &&
                    Objects.equals(documentTagValue, that.documentTagValue);
        }

        @Override
        public int hashCode() {

            return Objects.hash(documentTagID, documentTagValue);
        }
    }
}
