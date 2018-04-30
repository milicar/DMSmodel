package implementations;

import model.BusinessContact;
import model.process.hierarchy.Company;
import model.process.hierarchy.Process;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

    private Company company;
    private Process process;
    private BusinessContact contact;

    @Before
    public void setUp(){
        company = new Company("company");
        process = new Process("process");
        contact = new BusinessContact("contact");
    }

    @Test
    public void addProcess() {
        company.addProcess(process);

        Assert.assertTrue(company.getProcessList().contains(process));
    }

    @Test
    public void addBusinessContact() {
        company.addBusinessContact(contact);

        Assert.assertTrue(company.getBusinessContacts().contains(contact));
    }
}
