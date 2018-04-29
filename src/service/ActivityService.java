package service;

import exception.UnauthorisedUserException;
import model.document.DocumentType;
import model.process.hierarchy.Activity;
import model.User;
import model.process.hierarchy.Process;

import java.util.List;

public class ActivityService {


    public ActivityService(){}

    public Activity createActivity(User user, String activityName, String activityDescription, Process parentProcess) throws UnauthorisedUserException {
        final Activity createdActivity;

        if(isAuthorised(user) && isPrimary(parentProcess)){
            createdActivity = new Activity(activityName, activityDescription);
        } else throw new UnauthorisedUserException();
        return createdActivity;
    }

    private boolean isPrimary(Process parentProcess) {
        return true;
    }

    private boolean isAuthorised(User user) {
        return user.getRole() == User.Role.ADMIN;
    }

    public void addDocumentType(User user, Activity activity, Activity.Direction direction, DocumentType documentType) throws UnauthorisedUserException {
        if(isAuthorised(user)){
            activity.addActivityDocumentType(direction, documentType);
        } else throw new UnauthorisedUserException();
    }

    // Is this necessary?
    public void addDocumentTypes(User user, Activity activity, Activity.Direction direction, List<DocumentType> documentTypesList) throws UnauthorisedUserException {
        if(isAuthorised(user)){
            activity.addActivityDocumentTypes(direction, documentTypesList);
        } else throw new UnauthorisedUserException();
    }

    // should this be public?
    public List<DocumentType> getActivityDocumentTypeList(User user, Activity activity, Activity.Direction direction){
        //if isLoggedIn(user) ?
        return activity.getActivityDocumentTypes().get(direction);
    }

    public void getActivityByName(String activityName, Process parentProcess){
    }
}
