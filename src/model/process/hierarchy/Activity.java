package model.process.hierarchy;

import model.document.DocumentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity {
    private long activityID;
    private String activityName;
    private String activityDescription;
    private Map<Direction, List<DocumentType>> activityDocumentTypes;

    public enum Direction {IN, OUT}

    public Activity() {
        this("", "");
    }

    public Activity(String activityName) {
        this(activityName, "");
    }

    public Activity(String activityName, String activityDescription) {
        this.activityID = generateID();
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.activityDocumentTypes = initializeActivityDocumentTypes();

    }

    private HashMap<Direction, List<DocumentType>> initializeActivityDocumentTypes() {
        HashMap<Direction, List<DocumentType>> activityDocumentTypes = new HashMap<>();
        activityDocumentTypes.put(Direction.IN, new ArrayList<>());
        activityDocumentTypes.put(Direction.OUT, new ArrayList<>());
        return activityDocumentTypes;
    }

    private long generateID() {
        return 0L;
    }

    public long getActivityID() {
        return activityID;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public Map<Direction, List<DocumentType>> getActivityDocumentTypes() {
        return activityDocumentTypes;
    }

    public void addActivityDocumentTypes(Direction documentDirection, List<DocumentType> documentTypes) {
        for (DocumentType documentType : documentTypes)
            addActivityDocumentType(documentDirection, documentType);
    }

    public void addActivityDocumentType(Direction documentDirection, DocumentType documentType) {
        List<DocumentType> documentTypeList = activityDocumentTypes.get(documentDirection);
        documentTypeList.add(documentType);
        this.activityDocumentTypes.put(documentDirection, documentTypeList);
    }

    public void removeActivityDocumentType(Direction documentDirection, DocumentType documentType) {

        List<DocumentType> documentTypeList = activityDocumentTypes.get(documentDirection);
        documentTypeList.remove(documentType);
    }

}
