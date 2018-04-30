package service;

import exception.OperationNotAllowedException;
import exception.UnauthorisedUserException;
import model.document.DocumentType;
import model.process.hierarchy.Activity;
import model.User;
import model.process.hierarchy.Process;

import java.util.List;

public class ActivityService {


    public ActivityService() {
    }

    public Activity createActivity(User user, String activityName, String activityDescription, Process parentProcess)
            throws UnauthorisedUserException, OperationNotAllowedException {

        if (!isAuthorised(user)) throw new UnauthorisedUserException();
        if (parentProcess.isComplex()) throw new OperationNotAllowedException("Process is complex");

        return new Activity(activityName, activityDescription);
    }


    private boolean isAuthorised(User user) {
        return user.getRole() == User.Role.ADMIN;
    }

    public void addDocumentType(User user, Activity activity, Activity.Direction direction, DocumentType documentType) throws UnauthorisedUserException {
        if (!isAuthorised(user)) throw new UnauthorisedUserException();
        else {
            activity.addActivityDocumentType(direction, documentType);
        }
    }

    // Is this necessary?
    public void addDocumentTypes(User user, Activity activity, Activity.Direction direction, List<DocumentType> documentTypesList) throws UnauthorisedUserException {
        if (!isAuthorised(user)) throw new UnauthorisedUserException();
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
}
