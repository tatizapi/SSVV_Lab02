package Lab2IC.validation;


import Lab2IC.domain.Nota;
import Lab2IC.domain.Student;
import Lab2IC.domain.Tema;
import Lab2IC.repository.*;

public class NotaValidator implements Validator<Nota> {
    private StudentXMLRepo studentFileRepository;
    private TemaXMLRepo temaFileRepository;

    /**
     * Class constructor
     * @param studentFileRepository - repository student
     * @param temaFileRepository - repository tema
     */
    public NotaValidator(StudentXMLRepo studentFileRepository, TemaXMLRepo temaFileRepository) {
        this.studentFileRepository = studentFileRepository;
        this.temaFileRepository = temaFileRepository;
    }

    /**
     * Valideaza o nota
     * @param nota - nota pe care o valideaza
     * @throws ValidationException daca nota nu e valida
     */
    @Override
    public void validate(Nota nota) throws ValidationException {
        Student student = studentFileRepository.findOne(nota.getIdStudent());
        if (student== null){
            throw new ValidationException("Studentul nu exista!");
        }
        Tema tema = temaFileRepository.findOne(nota.getIdTema());
        if(tema == null){
            throw new ValidationException("Tema nu exista!");
        }
        double notaC = nota.getNota();
        if(notaC > 10.00 || notaC < 0.00){
            throw new ValidationException("Valoarea notei nu este corecta!");
        }
    }
}
