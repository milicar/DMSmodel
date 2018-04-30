package model.process.hierarchy;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String companyName;
    private List<Process> processList;

    public Company(String companyName) {
        this.companyName = companyName;
        this.processList = new ArrayList<>();
    }

    public void addProcess(Process process) {
        this.processList.add(process);
    }

    public List<Process> getProcessList() {
        return processList;
    }
}
