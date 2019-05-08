package Lab2IC;

import Lab2IC.domain.Nota;
import Lab2IC.domain.Student;
import Lab2IC.domain.Tema;
import Lab2IC.repository.NotaXMLRepo;
import Lab2IC.repository.StudentXMLRepo;
import Lab2IC.repository.TemaXMLRepo;
import Lab2IC.service.Service;
import Lab2IC.validation.NotaValidator;
import Lab2IC.validation.StudentValidator;
import Lab2IC.validation.TemaValidator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TopDownIntegrationTesting {

    private String filenameStudent = "fisiere/Studenti.xml";
    private String filenameTema = "fisiere/Teme.xml";
    private String filenameNota = "fisiere/Note.xml";

    private Service service;

    private TemaXMLRepo temaXMLRepository;
    private StudentXMLRepo studentXMLRepository;
    private NotaXMLRepo notaXMLRepository;

    private NotaValidator notaValidator;
    private StudentValidator studentValidator;
    private TemaValidator temaValidator;

    @Before
    public void setUp() {
        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository = new TemaXMLRepo(filenameTema);
        notaXMLRepository = new NotaXMLRepo(filenameNota);

        notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }


    // Tests


    // Add Student
    @Test
    public void td_canAddStudent() {
        Student student = new Student("id_stud", "TestName", 100, "testname@email.com");
        Student savedStudent = studentXMLRepository.save(student);

        assertNull(savedStudent);
        assertEquals(studentXMLRepository.findOne("id_stud"), student);

        //clean up
        studentXMLRepository.delete("id_stud");
    }


    // Add Assignment (Add Student + Add Assignment)
    @Test
    public void td_canAddAssignment() {
        Student student = new Student("id_stud", "TestName", 100, "testname@email.com");
        Student savedStudent = studentXMLRepository.save(student);

        assertNull(savedStudent);
        assertEquals(studentXMLRepository.findOne("id_stud"), student);

        Tema assignment = new Tema("id_tema", "TestDescription", 9, 8);
        Tema savedAssignment = temaXMLRepository.save(assignment);

        assertNull(savedAssignment);
        assertEquals(temaXMLRepository.findOne("id_tema"), assignment);

        //clean up
        studentXMLRepository.delete("id_stud");
        temaXMLRepository.delete("id_tema");
    }


    // Add Grade (Add Student + Add Assignment + Add Grade)
    @Test
    public void td_canAddGrade() {
        Student student = new Student("id_stud", "TestName", 100, "testname@email.com");
        Student savedStudent = studentXMLRepository.save(student);

        assertNull(savedStudent);
        assertEquals(studentXMLRepository.findOne("id_stud"), student);

        Tema assignment = new Tema("id_tema", "TestDescription", 9, 8);
        Tema savedAssignment = temaXMLRepository.save(assignment);

        assertNull(savedAssignment);
        assertEquals(temaXMLRepository.findOne("id_tema"), assignment);

        Nota grade = new Nota("id_nota", "id_stud", "id_tema", 10, LocalDate.of(2019, 5, 8));
        Nota savedGrade = notaXMLRepository.save(grade);

        assertNull(savedGrade);
        assertEquals(notaXMLRepository.findOne("id_nota"), grade);

        //clean up
        studentXMLRepository.delete("id_stud");
        temaXMLRepository.delete("id_tema");
        temaXMLRepository.delete("id_nota");
    }

}
