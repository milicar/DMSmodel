package service;

import exception.UnauthorisedUserException;
import model.User;
import model.document.DocumentType;

import java.util.Collections;
import java.util.List;

public class DocumentTypeService {

    public DocumentTypeService() {
    }


    private boolean isAuthorised(User user) {
        return user.getRole() == User.Role.ADMIN;
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
        if (isAuthorised(user)) {
            createdDocumentType = new DocumentType(documentTypeName, documentTypeModelLocation, documentTypeDescription);
            for (String descriptor : documentTypeDescriptors)
                createdDocumentType.addDocumentTypeDescriptor(descriptor);
        } else throw new UnauthorisedUserException();
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
        if (isAuthorised(user))
            documentType.removeDocumentTypeDescriptor(descriptorValue);
        else throw new UnauthorisedUserException();
    }

}
