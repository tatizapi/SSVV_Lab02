package Lab2IC;

import Lab2IC.domain.Student;
import Lab2IC.repository.NotaXMLRepo;
import Lab2IC.repository.StudentXMLRepo;
import Lab2IC.repository.TemaXMLRepo;
import Lab2IC.service.Service;
import Lab2IC.validation.NotaValidator;
import Lab2IC.validation.StudentValidator;
import Lab2IC.validation.TemaValidator;
import Lab2IC.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Service service;

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
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
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

}
