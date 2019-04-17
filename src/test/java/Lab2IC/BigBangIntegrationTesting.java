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
import Lab2IC.validation.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BigBangIntegrationTesting {

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


    // Add Student Functionality
    @Test
    public void canAddStudent() {
        service.deleteStudent("std1");
        Student student = new Student("std1", "TestStudent", 932, "teststudent@email.com");
        service.addStudent(student);

        assertEquals(service.findStudent("std1").getNume(), "TestStudent");
    }

    // Add Assignment Functionality
    @Test
    public void canAddAssignment() {
        service.deleteTema("asgn1");
        Tema assignment = new Tema("asgn1", "TestAssignment", 3, 1);
        service.addTema(assignment);

        assertEquals(service.findTema("asgn1").getDescriere(), "TestAssignment");
    }


    // Add Grade Functionality
    @Test
    public void canAddGrade()
    {
        Student student = new Student("abcdefg", "Abcdefg", 100, "email@mail.com");
        studentXMLRepository.save(student);
        Tema assignment = new Tema("tema0001", "descr", 9, 8);
        temaXMLRepository.save(assignment);

        Nota grade = new Nota("nota0001", "abcdefg", "tema0001", 10, LocalDate.of(2019, 04, 17));
        Nota savedGrade = notaXMLRepository.save(grade);

        assertNull(savedGrade);  // Save was successful
        assertEquals(notaXMLRepository.findOne("nota0001"), grade);  // Can find the grade

        // Clean Up
        studentXMLRepository.delete("abcdefg");
        temaXMLRepository.delete("tema0001");
        notaXMLRepository.delete("nota0001");
    }


    // Integrate All Functionalities
    @Test
    public void canIntegrateAddStudentAssignmentAndGrade()
    {
        // Student
        Student student = new Student("abcdefg", "Abcdefg", 100, "email@mail.com");
        Student savedStudent = studentXMLRepository.save(student);

        assertNull(savedStudent);  // Save was successful
        assertEquals(studentXMLRepository.findOne("abcdefg"), student);  // Can find the student

        // Assignment
        Tema assignment = new Tema("tema0001", "descr", 9, 8);
        Tema savedAssignment = temaXMLRepository.save(assignment);

        assertNull(savedAssignment);  // Save was successful
        assertEquals(temaXMLRepository.findOne("tema0001"), assignment);  // Can find the assignment

        // Grade
        Nota grade = new Nota("nota0001", "abcdefg", "tema0001", 10, LocalDate.of(2019, 04, 17));
        Nota savedGrade = notaXMLRepository.save(grade);

        assertNull(savedGrade);  // Save was successful
        assertEquals(notaXMLRepository.findOne("nota0001"), grade);  // Can find the grade

        // All
        assertEquals(grade.getIdStudent(), student.getID());
        assertEquals(grade.getIdTema(), assignment.getID());

        // Clean Up
        studentXMLRepository.delete("abcdefg");
        temaXMLRepository.delete("tema0001");
        notaXMLRepository.delete("nota0001");
    }




}
