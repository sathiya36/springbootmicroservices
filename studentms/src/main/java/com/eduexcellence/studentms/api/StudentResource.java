package com.eduexcellence.studentms.api;

import com.eduexcellence.studentms.model.Student;
import com.eduexcellence.studentms.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentResource.class);
    @Autowired
    private StudentRepo studentrepo;
    @GetMapping("/hello")
    public String getHello() {
        return "Hello World !";
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        LOGGER.info("Get All Students");
        return studentrepo.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        LOGGER.info("Get Student for the ID:" , id);
        Optional<Student> studentFound =studentrepo.findById(id);
        if (studentFound.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentFound.get());
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) throws URISyntaxException {
        LOGGER.info("Adding New Student:" , student);
        Student studentSaved = studentrepo.save(student);
        return ResponseEntity.created(new URI(student.getId().toString())).body(studentSaved);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent (@PathVariable Integer id,@RequestBody Student student) {
        Optional<Student> studentFound =studentrepo.findById(id);

        if (studentFound.isPresent()) {
            Student _student = studentFound.get();
            _student.setName(student.getName());
            _student.setGrade(student.getGrade());
            _student.setDob(student.getDob());
            return new ResponseEntity<>(studentrepo.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent (@PathVariable Integer id) {
    try {
    studentrepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
