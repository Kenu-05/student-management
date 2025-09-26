package com.example.student_management.repository;

import com.example.student_management.model.Course;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class CourseRepository {
    private static final String COLLECTION_NAME = "courses";

    public Course save(Course course) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        if (course.getId() == null || course.getId().isEmpty()) {
            DocumentReference docRef = db.collection(COLLECTION_NAME).document();
            course.setId(docRef.getId());
            docRef.set(course).get();
        } else {
            db.collection(COLLECTION_NAME).document(course.getId()).set(course).get();
        }
        return course;
    }

    public Course findById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot snapshot = db.collection(COLLECTION_NAME).document(id).get().get();
        return snapshot.exists() ? snapshot.toObject(Course.class) : null;
    }

    public List<Course> findAll() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> docs = future.get().getDocuments();
        List<Course> courses = new ArrayList<>();
        for (QueryDocumentSnapshot doc : docs) {
            courses.add(doc.toObject(Course.class));
        }
        return courses;
    }

    public void deleteById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(id).delete().get();
    }
}

