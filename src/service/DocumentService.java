package service;

import exception.UnauthorisedUserException;
import model.User;
import model.document.Document;
import model.document.DocumentType;

import java.util.Map;


public class DocumentService {

    public DocumentService() {
    }

    private boolean isAuthorised(User user) {
        // logged in user of any role is authorised
        return true;
    }

    public Document createDocument(User user, String documentName, String documentLocation,
                                   DocumentType documentType, Map<String, String> descriptors)
            throws UnauthorisedUserException {

        final Document createdDocument;
        if (isAuthorised(user)) {
            createdDocument = new Document(documentName, documentLocation, documentType);
            createdDocument.addDocumentDescriptors(descriptors);
        } else throw new UnauthorisedUserException();
        return createdDocument;
    }

//    public void addDocumentDescriptor(User user, Document document,
//                                      DocumentType.DocumentTypeDescriptor documentTypeDescriptor,
//                                      String descriptorValue)
//            throws UnauthorisedUserException {
//
//        if (isAuthorised(user))
//            document.addDocumentDescriptorValue(documentTypeDescriptor, descriptorValue);
//        else throw new UnauthorisedUserException();
//    }
}