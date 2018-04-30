package model.process.hierarchy;

import exception.OperationNotAllowedException;

import java.util.ArrayList;
import java.util.List;

public class Process {

    private long processID;
    private String processName;
    private String processShortDescription;
    private long parentID;

    private List<Process> subprocesses;

    private List<Activity> activities;

    public Process(long processID, String processName, String processShortDescription) {
        this.processID = processID;
        this.processName = processName;
        this.processShortDescription = processShortDescription;
    }

    public Process(String processName, String processShortDescription) {
        this(9L, processName, processShortDescription);
        this.processID = generateProcessID();
    }

    public Process(String processName) {
        this(9L, processName, "");
        this.processID = generateProcessID();
    }

    private long generateProcessID() {
        return 9L;
    }

    public long getProcessID() {
        return processID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    public String getProcessName() {
        return processName;
    }

    public String getProcessShortDescription() {
        return processShortDescription;
    }

    public long getParentID() {
        return parentID;
    }

    public List<Process> getSubprocessesList() {
        return subprocesses;
    }

    public void addSubprocess(Process subprocess) throws OperationNotAllowedException {
        if (isPrimitive()) throw new OperationNotAllowedException("Process is primary");
        if (getSubprocessesList() == null) {
            subprocesses = new ArrayList<>();
        }
        subprocesses.add(subprocess);
        subprocess.setParentID(this.getProcessID());
    }

    public boolean isPrimitive() {
        return (activities != null) && activities.size() > 0;
    }

    public void removeSubprocess(Process subprocess) {
        subprocesses.remove(subprocess);
    }

    public List<Activity> getActivitiesList() {
        return activities;
    }

    public void addActivity(Activity activity) throws OperationNotAllowedException {
        if (isComplex()) throw new OperationNotAllowedException("Process is complex");
        if (getActivitiesList() == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
    }

    public boolean isComplex() {
        return (subprocesses != null) && subprocesses.size() > 0;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }
}
