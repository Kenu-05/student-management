package com.example.student_management.service;

import com.example.student_management.exception.ResourceNotFoundException;
import com.example.student_management.model.Course;
import com.example.student_management.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course create(Course course) throws ExecutionException, InterruptedException {
        return courseRepository.save(course);
    }

    public Course getById(String id) throws ExecutionException, InterruptedException {
        Course course = courseRepository.findById(id);
        if (course == null) throw new ResourceNotFoundException("Course not found: " + id);
        return course;
    }

    public List<Course> getAll() throws ExecutionException, InterruptedException {
        return courseRepository.findAll();
    }

    public Course update(Course course) throws ExecutionException, InterruptedException {
        if (course.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for update");
        }
        return courseRepository.save(course);
    }

    public void delete(String id) throws ExecutionException, InterruptedException {
        courseRepository.deleteById(id);
    }
}
