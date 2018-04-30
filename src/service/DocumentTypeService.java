package service;

import exception.UnauthorisedUserException;
import model.CheckUser;
import model.User;
import model.document.DocumentType;

import java.util.Collections;
import java.util.List;

public class DocumentTypeService implements CheckUser {

    public DocumentTypeService() {
    }


    private String generateID() {
        // NOT YET IMPLEMENTED
        String stringPartId = "dt";
        int intPartID = 0;
        // try to get last existing id and then generate new
        return stringPartId + intPartID;
    }

    public DocumentType createDocumentType(User user, String documentTypeName)
            throws UnauthorisedUserException {
        return createDocumentType(user, 0L, documentTypeName, "", "", Collections.emptyList());
    }

    public DocumentType createDocumentType(User user,
                                           long documentTypeID,
                                           String documentTypeName,
                                           String documentTypeModelLocation,
                                           String documentTypeDescription, List<String> documentTypeDescriptors) throws UnauthorisedUserException {
        final DocumentType createdDocumentType;
        if (isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();
        else {
            createdDocumentType = new DocumentType(documentTypeName, documentTypeModelLocation, documentTypeDescription);
            for (String descriptor : documentTypeDescriptors)
                createdDocumentType.addDocumentTypeDescriptor(descriptor);
        }
        return createdDocumentType;
    }


//    private void addDocumentTypeDescriptor(User user, DocumentType documentType,
//                                          String descriptorValue) throws UnauthorisedUserException {
//        if (isAuthorised(user))
//            documentType.addDocumentTypeDescriptor(descriptorValue);
//        else throw new UnauthorisedUserException();
//    }

    public void removeDocumentTypeDescriptor(User user, DocumentType documentType,
                                             String descriptorValue) throws UnauthorisedUserException {
        if (isNotAuthorised(user) || isNotLoggedIn(user)) throw new UnauthorisedUserException();
        else {
            documentType.removeDocumentTypeDescriptor(descriptorValue);
        }
    }

    @Override
    public boolean isNotAuthorised(User user) {
        return user.getRole() != User.Role.ADMIN;
    }

    @Override
    public boolean isNotLoggedIn(User user) {
        return false;
    }
}
