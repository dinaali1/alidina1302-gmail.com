
package com.backendSOA.backendSOA.controllers;

import com.backendSOA.backendSOA.models.Student;
import com.backendSOA.backendSOA.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public ResponseEntity<?> all() {
        try {
            return ResponseEntity.ok((List<Student>) studentRepository.findAll());
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting all Students");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok((Student) studentRepository.findById(id).orElse(null));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting Students");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student studentInfo) {
        try {
            Student student = new Student();
            student.setEmail(studentInfo.getEmail());
            student.setPassword(studentInfo.getPassword());
            student.setRole(studentInfo.getRole());
            student.setFirstName(studentInfo.getFirstName());
            student.setLastName(studentInfo.getLastName());
            student.setBirthDate(studentInfo.getBirthDate());
            student.setNumCarteEtudiant(studentInfo.getNumCarteEtudiant());
            return ResponseEntity.ok(studentRepository.save(student));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error signing up");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@Valid @PathVariable Long id, @RequestBody Student studentInfo) {
        try {
            Student student = studentRepository.findById(id).orElse(null);
            if (student != null) {
                studentInfo.setId(id);
                return ResponseEntity.ok(studentRepository.save(studentInfo));
            } else {
                return ResponseEntity.ok(studentRepository.save(studentInfo));
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            Student student = studentRepository.findById(id).orElse(null);
            if (student != null) {
                studentRepository.delete(student);
                return ResponseEntity.ok("Student deleted");
            } else {
                throw new Error("Student does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }
}