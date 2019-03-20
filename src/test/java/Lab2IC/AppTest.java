package Lab2IC;

import Lab2IC.domain.Student;
import Lab2IC.repository.NotaXMLRepo;
import Lab2IC.repository.StudentXMLRepo;
import Lab2IC.repository.TemaXMLRepo;
import Lab2IC.service.Service;
import Lab2IC.validation.NotaValidator;
import Lab2IC.validation.StudentValidator;
import Lab2IC.validation.TemaValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Service service;
    Student student;

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
        student = new Student("id1", "Nume", 999, "email@something.com");

        assertNull(service.addStudent(student));
    }

    @Test
    public void addStudentShouldReturnStudentOnFail() {
        service.deleteStudent("id1");
        Student student1 = new Student("id1", "Nume", 999, "email@something.com");

        assertNull(service.addStudent(student1));
        assertNotNull(service.addStudent(student1));
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
}
