package service;

import exception.OperationNotAllowedException;
import exception.UnauthorisedUserException;
import model.User;
import model.process.hierarchy.Company;
import model.process.hierarchy.Process;

public class ProcessService {

    public ProcessService() {
    }

    public Process createProcess(User user, Object parent, String processName, String processDescription) throws OperationNotAllowedException, UnauthorisedUserException {

        if (!isAuthorised(user)) throw new UnauthorisedUserException();

        if (parent instanceof Process) {
            return createSubprocess((Process) parent, processName, processDescription);
        } else if (parent instanceof Company) {
            return createCompanyProcess((Company) parent, processName, processDescription);

        } else throw new IllegalArgumentException();
    }

    private Process createSubprocess(Process parent, String processName, String processDescription) throws OperationNotAllowedException {
        if (parent.isPrimitive()) throw new OperationNotAllowedException("Process is primitive");
        else {
            Process createdProcess = new Process(processName, processDescription);
            parent.addSubprocess(createdProcess);
            return createdProcess;
        }
    }

    private Process createCompanyProcess(Company parent, String processName, String processDescription) {
        Process createdProcess = new Process(processName, processDescription);
        parent.addProcess(createdProcess);
        return createdProcess;
    }


    private boolean isAuthorised(User user) {
        return user.getRole() == User.Role.ADMIN;
    }
}