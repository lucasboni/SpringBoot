package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.CustomErroType;
import br.com.devdojo.awesome.mock.StudentMock;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController//diz que é um endpoint rest
@RequestMapping("students")
public class StudentEndPoint {

    @Autowired
    private DateUtil dateUtil;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll(){
        //System.out.println(dateUtil.formateLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(StudentMock.studentList, HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET,path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id){

        int index= StudentMock.studentList.indexOf(new Student(id));

        if(index== -1){
            return new ResponseEntity<>(new CustomErroType("Student not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(StudentMock.studentList.get(index), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
        StudentMock.studentList.add(student);
        return new ResponseEntity<>(student.getId(),HttpStatus.OK);
    }


    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student){
        StudentMock.studentList.remove(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        StudentMock.studentList.remove(student);
        StudentMock.studentList.add(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
