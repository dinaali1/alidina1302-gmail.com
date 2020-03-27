
package com.backendSOA.backendSOA.controllers;

import com.backendSOA.backendSOA.models.Professor;
import com.backendSOA.backendSOA.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/")
    public ResponseEntity<?> all() {
        try {
            return ResponseEntity.ok((List<Professor>) professorRepository.findAll());
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting all professors");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok((Professor) professorRepository.findById(id).orElse(null));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting professors");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createProfessor(@Valid @RequestBody Professor professorInfo) {
        try {
            Professor professor = new Professor();
            professor.setEmail(professorInfo.getEmail());
            professor.setPassword(professorInfo.getPassword());
            professor.setRole(professorInfo.getRole());
            professor.setFirstName(professorInfo.getFirstName());
            professor.setLastName(professorInfo.getLastName());
            professor.setBirthDate(professorInfo.getBirthDate());
            professor.setSubject(professorInfo.getSubject());
            return ResponseEntity.ok(professorRepository.save(professor));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error signing up");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfessor(@Valid @PathVariable Long id, @RequestBody Professor professorInfo) {
        try {
            Professor professor = professorRepository.findById(id).orElse(null);
            if (professor != null) {
                professorInfo.setId(id);
                return ResponseEntity.ok(professorRepository.save(professorInfo));
            } else {
                return ResponseEntity.ok(professorRepository.save(professorInfo));
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfessor(@PathVariable Long id) {
        try {
            Professor professor = professorRepository.findById(id).orElse(null);
            if (professor != null) {
                professorRepository.delete(professor);
                return ResponseEntity.ok("professor deleted");
            } else {
                throw new Error("professor does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }
}