package me.zeph.spring.data.redis;

import me.zeph.spring.data.redis.model.Student;
import me.zeph.spring.data.redis.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/students/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        Optional<Student> studentOptional = studentRepository.findByName(name);
        if (studentOptional.isPresent()) {
            return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> findStudentByGenderAndName(@RequestParam String name, @RequestParam String gender) {
        return new ResponseEntity<>(studentRepository.findByNameAndGender(name, gender), HttpStatus.OK);
    }

    @GetMapping(value = "/students/delete/{id}")
    public ResponseEntity<List<Student>> findStudentByGenderAndName(@PathVariable String id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/students/gender/{gender}")
    public ResponseEntity<List<Student>> getStudentByGender(@PathVariable String gender) {
        List<Student> studentOptional = studentRepository.findByGender(Student.Gender.valueOf(gender));
        return new ResponseEntity<>(studentOptional, HttpStatus.OK);
    }


    @PostMapping(value = "/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }
}
