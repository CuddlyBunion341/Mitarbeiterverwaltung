package test;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static junit.framework.TestCase.assertEquals;

public class CompanyTest {

    private static Company company;

    @Before
    public void setUp(){
        company = new Company();
    }

    @Test
    public void employeesNotEmpty() {
        Vector<Person> employees = company.getEmployees();
        assertEquals(employees.size() > 0);
    }

    @Test
    public void employeeExists() throws NullPointerException {
        Vector<Person> employees = DataHandler.getEmployees();
        Person person = employees.get(0);
        assertEquals(person.getFirstName(), "Jenith");
    }
    @Test
    public void teamsTestNotEmpty() throws NullPointerException {
        Vector<String> teams = DataHandler.getTeams();
        assertEquals(teams.size() > 0);
    }
    @Test
    public void departmentsNotEmpty() throws NullPointerException {
        Vector<String> departements = DataHandler.getDepartments();
        assert(departements.size() > 0);
    }

}