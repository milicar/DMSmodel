package model;

public class BusinessContact {

    private long contactID;
    private String contactName;
    private String contactInformation;
    private String contactAddress;
    private String contactEmail;

    public BusinessContact(String contactName) {
        this(contactName, "", "", "");
    }

    public BusinessContact(String contactName, String contactInformation, String contactAddress, String contactEmail){
        this.contactID = generateContactID();
        this.contactName = contactName;
        this.contactInformation = contactInformation;
        this.contactAddress = contactAddress;
        this.contactEmail = contactEmail;
    }

    private long generateContactID() {
        return 8L;
    }
}
