package com.example.student_management.controller;

import com.example.student_management.model.Course;
import com.example.student_management.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course")
    public ResponseEntity<Course> create(@RequestBody Course course) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.create(course));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.getAll());
    }

    @PutMapping("/course")
    public ResponseEntity<Course> update(@RequestBody Course course) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.update(course));
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        courseService.delete(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}

