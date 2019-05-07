package Lab2IC;

import Lab2IC.repository.NotaXMLRepo;
import Lab2IC.repository.StudentXMLRepo;
import Lab2IC.repository.TemaXMLRepo;
import Lab2IC.service.Service;
import Lab2IC.validation.NotaValidator;
import Lab2IC.validation.StudentValidator;
import Lab2IC.validation.TemaValidator;
import org.junit.Before;
import org.junit.Test;

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

    }


    // Add Assignment (Add Student + Add Assignment)
    @Test
    public void td_canAddAssignment() {

    }


    // Add Grade (Add Student + Add Assignment + Add Grade)
    @Test
    public void td_canAddGrade() {

    }

}
