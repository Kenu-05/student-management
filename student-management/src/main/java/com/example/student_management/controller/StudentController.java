package com.example.student_management.controller;

import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public ResponseEntity<Student> create(@RequestBody Student student) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(studentService.create(student));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PutMapping("/student")
    public ResponseEntity<Student> update(@RequestBody Student student) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        studentService.delete(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}

