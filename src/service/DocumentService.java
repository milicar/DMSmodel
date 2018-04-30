package service;

import exception.UnauthorisedUserException;
import model.CheckUser;
import model.User;
import model.document.Document;
import model.document.DocumentType;

import java.util.Map;


public class DocumentService implements CheckUser {

    public DocumentService() {
    }


    public Document createDocument(User user, String documentName, String documentLocation,
                                   DocumentType documentType, Map<String, String> descriptors)
            throws UnauthorisedUserException {

        final Document createdDocument;
        if (isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();
        else {
            createdDocument = new Document(documentName, documentLocation, documentType);
            createdDocument.addDocumentDescriptorValues(descriptors);
        }
        return createdDocument;
    }

//    public void addDocumentDescriptor(User user, Document document,
//                                      DocumentType.Descriptor documentTypeDescriptor,
//                                      String descriptorValue)
//            throws UnauthorisedUserException {
//
//        if (isAuthorised(user))  //changed to isNotAuthorised
//            document.addDocumentDescriptorValue(documentTypeDescriptor, descriptorValue);
//        else throw new UnauthorisedUserException();
//    }

    public void addDocumentTag(User user, Document document, String tagValue) throws UnauthorisedUserException {
        if (isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();
        else {
            document.addDocumentTag(tagValue);
        }
    }

    public void removeDocumentTag(User user, Document document, String tagValue) throws UnauthorisedUserException {
        if (isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();
        else {
            document.removeDocumentTag(tagValue);
        }
    }

    @Override
    public boolean isNotAuthorised(User user) {
        return false;
    }

    @Override
    public boolean isNotLoggedIn(User user) {
        return false;
    }
}
