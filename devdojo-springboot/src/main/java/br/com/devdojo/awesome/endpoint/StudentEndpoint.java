package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.ResorceNotFoundException;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.repository.StudentRepository;
import br.com.devdojo.awesome.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController//diz que é um endpoint rest
@RequestMapping("students")
public class StudentEndpoint {

   // @Autowired
    //private DateUtil dateUtil;


    private final StudentRepository studentRepository;

    @Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll(){
        //System.out.println(dateUtil.formateLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET,path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") long id) {
        verifyIfStudentExists(id);
        return new ResponseEntity<>(studentRepository.findById(id).get(), HttpStatus.OK);

    }

    @GetMapping(path = "findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable String name){
        return new ResponseEntity<>(studentRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @Transactional(rollbackFor =Exception.class)//faz o rollback caso de algum erro evitando que seja salvo algo pela metade
    public ResponseEntity<?> save(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentRepository.save(student),HttpStatus.CREATED);
    }




    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        verifyIfStudentExists(id);
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new ResorceNotFoundException("Student not found ID: " + id);
        }
    }
}
