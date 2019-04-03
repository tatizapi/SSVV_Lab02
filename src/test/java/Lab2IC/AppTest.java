package Lab2IC;

import Lab2IC.domain.Student;
import Lab2IC.domain.Tema;
import Lab2IC.repository.NotaXMLRepo;
import Lab2IC.repository.StudentXMLRepo;
import Lab2IC.repository.TemaXMLRepo;
import Lab2IC.service.Service;
import Lab2IC.validation.NotaValidator;
import Lab2IC.validation.StudentValidator;
import Lab2IC.validation.TemaValidator;
import Lab2IC.validation.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private Service service;
    private String veryLongString;
    TemaXMLRepo temaXMLRepository;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void addStudentShouldReturnNullOnSuccess() {
        service.deleteStudent("id1");
        Student student = new Student("id1", "Nume", 999, "email@something.com");

        assertNull(service.addStudent(student));
    }

    @Test
    public void addStudentShouldReturnStudentOnFail() {
        service.deleteStudent("id1");
        Student student = new Student("id1", "Nume", 999, "email@something.com");

        assertNull(service.addStudent(student));
        assertNotNull(service.addStudent(student));
    }

    @Before
    public void setUp() {

        // Set up the service

        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);


        // Set up the very long array
        char[] longArray = new char[100000000];
        for (int i = 0; i < longArray.length; i++)
        {
            longArray[i] = 'a';
        }
        veryLongString = Arrays.toString(longArray);


        // Clean the files before tests
        Iterable<Student> students = service.getAllStudenti();
        for (Student student : students) {
            service.deleteStudent(student.getID());
        }
    }

    @After
    public void tearDown()
    {
        // Clean the files after tests
        Iterable<Student> students = service.getAllStudenti();
        for (Student student : students) {
            service.deleteStudent(student.getID());
        }
    }

    // --- EC test cases ---

    //No. 2
    @Test(expected = ValidationException.class)
    public void addStudentIdEmptyShouldRaiseException() {
        Student student = new Student("", "Name", 10, "name@email.com");
        service.addStudent(student);
    }

    //No. 3
    @Test(expected = ValidationException.class)
    public void addStudentIdContainsSymbolsShouldRaiseException() {
        Student student = new Student("-1.3", "Name", 10, "name@email.com");
        service.addStudent(student);
    }

    //No. 4
    @Test(expected = ValidationException.class)
    public void addStudentNameEmptyStringShouldRaiseException() {
        Student student = new Student("id1", "", 10, "name@email.com");
        service.addStudent(student);
    }

    //No. 5
    @Test(expected = ValidationException.class)
    public void addStudentNameContainsSymbolsShouldRaiseException() {
        Student student = new Student("id1", "name*", 10, "name@email.com");
        service.addStudent(student);
    }

    //No. 6
    @Test(expected = ValidationException.class)
    public void addStudentNameContainsDigitsShouldRaiseException() {
        Student student = new Student("id1", "name2", 10, "name@email.com");
        service.addStudent(student);
    }

    //No. 7
    @Test(expected = ValidationException.class)
    public void addStudentGroupIsZeroShouldRaiseException() {
        Student student = new Student("id1", "name", 0, "name@email.com");
        service.addStudent(student);
    }

    //No. 8
    @Test(expected = ValidationException.class)
    public void addStudentGroupIsNegativeShouldRaiseException() {
        Student student = new Student("id1", "name", -2, "name@email.com");
        service.addStudent(student);
    }

    //No. 9
    @Test(expected = ValidationException.class)
    public void addStudentEmailRespectsFormatShouldRaiseException() {
        Student student = new Student("id1", "name", 10, "name");
        service.addStudent(student);
    }



    // --- BVE Test Cases ---

    // Tests on id

    @Test(expected = ValidationException.class)
    public void addStudentNullIdShouldRaiseException()
    {
        Student student = new Student(null, "TestName", 1, "student@email.com");
        service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentTooLongIdShouldRaiseException()
    {
        Student student = new Student(veryLongString, "TestName", 1, "student@email.com");
        service.addStudent(student);
    }


    // Tests on Name

    @Test(expected = ValidationException.class)
    public void addStudentNullNameShouldRaiseException()
    {
        Student student = new Student("1", null, 1, "student@email.com");
        service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentTooLongNameShouldRaiseException()
    {
        Student student = new Student("1", veryLongString, 1, "student@email.com");
        service.addStudent(student);
    }


    // Tests on Email

    @Test(expected = ValidationException.class)
    public void addStudentNullEmailShouldRaiseException()
    {
        Student student = new Student("1", "TestName", 1, null);
        service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentEmptyStringEmailShouldRaiseException()
    {
        Student student = new Student("1", "TestName", 1, "");
        service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentTooLongEmailShouldRaiseException()
    {
        Student student = new Student("1", "TestName", 1, veryLongString);
        service.addStudent(student);
    }


    // Tests on Group

    @Test
    public void addStudentNaturalNumberAsGroupShouldNotRaiseException()
    {
        Student student = new Student("1", "TestName", 1, "student@email.com");
        service.addStudent(student);
    }

    @Test
    public void addStudentMAXINTAsGroupShouldNotRaiseException()
    {
        Student student = new Student("1", "TestName", Integer.MAX_VALUE, "student@email.com");
        service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentMAXINTPlusOneAsGroupShouldRaiseException()
    {
        Student student = new Student("1", "TestName", Integer.MAX_VALUE + 1, "student@email.com");
        service.addStudent(student);
    }


    // --- WHITE BOX TESTING ---
    @Test
    public void addAssignmentInRepoShouldReturnNullOnSuccess() {
        temaXMLRepository.delete("100");

        Tema assignment = new Tema("100", "TestDescription", 3, 1);
        assertNull(temaXMLRepository.save(assignment));

        temaXMLRepository.delete("100");
    }

    @Test
    public void addAssignmentInRepoShouldReturnEntityOnFail() {
        temaXMLRepository.delete("100");

        Tema assignment = new Tema("100", "TestDescription", 3, 1);
        temaXMLRepository.save(assignment);
        assertEquals(temaXMLRepository.save(assignment).getID(), assignment.getID());

        temaXMLRepository.delete("100");
    }
}
