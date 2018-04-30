package model.document;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocumentType {

    private long documentTypeID;
    private String name;
    private Path modelLocation;
    private String shortDescription;
    private List<Descriptor> documentTypeDescriptors;

    public DocumentType() {
        this("", "", "");
    }

    public DocumentType(String name) {
        this(name, "", "");
    }

    public DocumentType(String name, String modelLocation, String shortDescription) {
        documentTypeID = generateID();
        this.name = name;
        this.modelLocation = Paths.get(modelLocation);
        this.shortDescription = shortDescription;
        documentTypeDescriptors = new ArrayList<>();
    }

    private long generateID() {
        return 0L;
    }

    public List<Descriptor> getDocumentTypeDescriptors() {
        return documentTypeDescriptors;
    }

    public void addDocumentTypeDescriptor(String dtDescriptorName) {
        documentTypeDescriptors.add(new Descriptor(dtDescriptorName));

    }

    public void removeDocumentTypeDescriptor(String dtDescriptorName) {
        for (Descriptor descriptor : documentTypeDescriptors) {
            if (dtDescriptorName.equals(descriptor.getDocumentTypeDescriptorName()))
                documentTypeDescriptors.remove(descriptor);
        }
    }

    public Descriptor findDocumentTypeDescriptor(String descriptorName) {
        for (Descriptor descriptor : getDocumentTypeDescriptors()) {
            if (descriptorName.equals(descriptor.getDocumentTypeDescriptorName()))
                return descriptor;
        }
        return null;
    }

    public long getDocumentTypeID() {
        return this.documentTypeID;
    }

    public String getDocumentTypeName() {
        return this.name;
    }

    public Path getDocumentTypeModelLocation() {
        return this.modelLocation;
    }

    public String getDocumentTypeDescription() {
        return this.shortDescription;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "documentTypeID='" + documentTypeID + '\'' +
                ", name='" + name + '\'' +
                ", modelLocation='" + modelLocation + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", documentTypeDescriptors=" + documentTypeDescriptors +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        DocumentType that = (DocumentType) other;
        return (this.getDocumentTypeID() == that.getDocumentTypeID()) &&
                this.getDocumentTypeName().equals(that.getDocumentTypeName()) &&
                this.getDocumentTypeModelLocation().equals(that.getDocumentTypeModelLocation()) &&
                this.getDocumentTypeDescriptors().equals(that.getDocumentTypeDescriptors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumentTypeID(), name, modelLocation, getDocumentTypeDescriptors());
    }

    public class Descriptor {

        private long documentTypeDescriptorID;
        private String documentTypeDescriptorName;

        public Descriptor() {
            this(1L, "::default name::");
        }

        public Descriptor(long documentTypeDescriptorID, String documentTypeDescriptorName) {
            this.documentTypeDescriptorID = documentTypeDescriptorID;
            this.documentTypeDescriptorName = documentTypeDescriptorName;
        }

        public Descriptor(String dtDescriptorName) {
            this(1L, dtDescriptorName);
        }

        public long getDocumentTypeDescriptorID() {
            return documentTypeDescriptorID;
        }

        public String getDocumentTypeDescriptorName() {
            return documentTypeDescriptorName;
        }

        @Override
        public String toString() {
            return "DescriptorName='" + documentTypeDescriptorName + '\'';
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Descriptor that = (Descriptor) other;
            return Objects.equals(getDocumentTypeDescriptorID(), that.getDocumentTypeDescriptorID()) &&
                    Objects.equals(getDocumentTypeDescriptorName(), that.getDocumentTypeDescriptorName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getDocumentTypeDescriptorID(), getDocumentTypeDescriptorName());
        }
    }
}
