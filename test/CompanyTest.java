import data.DataHandler;
import model.company.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static junit.framework.TestCase.assertEquals;

public class CompanyTest {

    private static CompanyTest employees;
    private static CompanyTest teams;

    private static CompanyTest departements;


    public static void main(String[] args) {

    }

    @Before
    public void setUp(){
        employees = new CompanyTest();
        teams = new CompanyTest();
        departements = new CompanyTest();
    }

    @Test
    public void TestCompany() throws NullPointerException {
        Vector<Person> employees = DataHandler.getEmployees();
        assertEquals(Person.getName(), "Jenith");
    }
    @Test
    public void TestCompany2() throws NullPointerException {
        Vector<String> teams = DataHandler.getTeams();

    }
    @Test
    public void TestCompany3() throws NullPointerException {
        Vector<String> departements = DataHandler.getDepartments();

    }

}