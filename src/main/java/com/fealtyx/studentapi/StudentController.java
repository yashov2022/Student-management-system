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

    @Autowired
    private OllamaService ollamaService;

    // CREATE student
    @PostMapping
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok("Student saved");
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {

        Student student = studentRepository.findById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // ✅ OLLAMA SUMMARY API
    @GetMapping("/{id}/summary")
    public ResponseEntity<String> getStudentSummary(@PathVariable int id) {

        Student student = studentRepository.findById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        String summary = ollamaService.generateStudentSummary(student);
        return ResponseEntity.ok(summary);
    }

    // UPDATE student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student student) {

        int rowsAffected = studentRepository.update(id, student);

        if (rowsAffected > 0) {
            return ResponseEntity.ok("Student updated");
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        int rowsAffected = studentRepository.delete(id);

        if (rowsAffected > 0) {
            return ResponseEntity.ok("Student deleted");
        }

        return ResponseEntity.notFound().build();
    }
}
