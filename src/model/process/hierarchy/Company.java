package model.process.hierarchy;

import model.BusinessContact;

import javax.naming.directory.Attribute;
import java.util.ArrayList;
import java.util.List;

public class Company {

    private long companyID;
    private String companyName;
    private String companyAddress;
    private String companyDescription;
    private List<Process> processList;
    private List<BusinessContact> businessContactList;

    public Company(String companyName) {
        this(companyName, "", "");
    }

    public Company(String companyName, String companyAddress, String companyDescription){
        this.companyID = generateID();
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyDescription = companyDescription;
        this.processList = new ArrayList<>();
        this.businessContactList = new ArrayList<>();
    }

    private long generateID(){
        return 6L;
    }

    public long getCompanyID(){
        return companyID;
    }

    public void addProcess(Process process) {
        this.processList.add(process);
    }

    public List<Process> getProcessList() {
        return processList;
    }

    public void addBusinessContact(BusinessContact contact) {
        businessContactList.add(contact);
    }

    public List<BusinessContact> getBusinessContacts() {
        return businessContactList;
    }
}
