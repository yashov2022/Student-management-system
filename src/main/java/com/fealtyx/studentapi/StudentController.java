package com.fealtyx.studentapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @PostMapping
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok("Student saved");
    }
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        try {
            Student student = studentRepository.findById(id);
            return ResponseEntity.ok(student);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student student) {
        int rowsAffected = studentRepository.update(id, student);
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Student updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        int rowsAffected = studentRepository.delete(id);
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Student deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
