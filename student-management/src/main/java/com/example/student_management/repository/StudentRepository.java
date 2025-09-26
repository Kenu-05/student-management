package com.example.student_management.repository;

import com.example.student_management.model.Student;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class StudentRepository {
    private static final String COLLECTION_NAME = "students";

    public Student save(Student student) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        if (student.getId() == null || student.getId().isEmpty()) {
            DocumentReference docRef = db.collection(COLLECTION_NAME).document();
            student.setId(docRef.getId());
            docRef.set(student).get();
        } else {
            db.collection(COLLECTION_NAME).document(student.getId()).set(student).get();
        }
        return student;
    }

    public Student findById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot snapshot = db.collection(COLLECTION_NAME).document(id).get().get();
        return snapshot.exists() ? snapshot.toObject(Student.class) : null;
    }

    public List<Student> findAll() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> docs = future.get().getDocuments();
        List<Student> students = new ArrayList<>();
        for (QueryDocumentSnapshot doc : docs) {
            students.add(doc.toObject(Student.class));
        }
        return students;
    }

    public void deleteById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(id).delete().get();
    }
}

