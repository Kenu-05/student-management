package com.example.student_management.service;

import com.example.student_management.exception.ResourceNotFoundException;
import com.example.student_management.model.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) throws ExecutionException, InterruptedException {
        return studentRepository.save(student);
    }

    public Student getById(String id) throws ExecutionException, InterruptedException {
        Student student = studentRepository.findById(id);
        if (student == null) throw new ResourceNotFoundException("Student not found: " + id);
        return student;
    }

    public List<Student> getAll() throws ExecutionException, InterruptedException {
        return studentRepository.findAll();
    }

    public Student update(Student student) throws ExecutionException, InterruptedException {
        if (student.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for update");
        }
        return studentRepository.save(student);
    }

    public void delete(String id) throws ExecutionException, InterruptedException {
        studentRepository.deleteById(id);
    }
}
