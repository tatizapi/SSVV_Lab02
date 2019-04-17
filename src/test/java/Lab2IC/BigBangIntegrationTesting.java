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



    // Add Assignment Functionality



    // Add Grade Functionality



    // Integrate All Functionalities




}
