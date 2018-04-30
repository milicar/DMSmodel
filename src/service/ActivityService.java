package service;

import exception.OperationNotAllowedException;
import exception.UnauthorisedUserException;
import model.CheckUser;
import model.document.DocumentType;
import model.process.hierarchy.Activity;
import model.User;
import model.process.hierarchy.Process;

import java.util.List;

public class ActivityService implements CheckUser {


    public ActivityService() {
    }

    public Activity createActivity(User user, String activityName, String activityDescription, Process parentProcess)
            throws UnauthorisedUserException, OperationNotAllowedException {

        if (isNotAuthorised(user)) throw new UnauthorisedUserException();
        if (parentProcess.isComplex()) throw new OperationNotAllowedException("Process is complex");

        return new Activity(activityName, activityDescription);
    }

    public void addDocumentType(User user, Activity activity, Activity.Direction direction, DocumentType documentType) throws UnauthorisedUserException {
        if (isNotAuthorised(user)) throw new UnauthorisedUserException();
        else {
            activity.addActivityDocumentType(direction, documentType);
        }
    }

    // Is this necessary?
    public void addDocumentTypes(User user, Activity activity, Activity.Direction direction, List<DocumentType> documentTypesList) throws UnauthorisedUserException {
        if (isNotAuthorised(user)) throw new UnauthorisedUserException();
        else {
            activity.addActivityDocumentTypes(direction, documentTypesList);
        }
    }

    // should this be public?
    public List<DocumentType> getActivityDocumentTypeList(User user, Activity activity, Activity.Direction direction) {
        //if isLoggedIn(user) ?
        return activity.getActivityDocumentTypes().get(direction);
    }

    public void getActivityByName(String activityName, Process parentProcess) {
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
