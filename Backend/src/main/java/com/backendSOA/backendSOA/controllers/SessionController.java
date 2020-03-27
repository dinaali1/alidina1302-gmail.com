
package com.backendSOA.backendSOA.controllers;

import com.backendSOA.backendSOA.models.Professor;
import com.backendSOA.backendSOA.models.Session;
import com.backendSOA.backendSOA.repositories.ProfessorRepository;
import com.backendSOA.backendSOA.repositories.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/")
    public ResponseEntity<?> all() {
        try {
            List<?> list = sessionRepository.getProfessorsPresence();
            return ResponseEntity.ok((List<?>) sessionRepository.findAll());
        } catch (final Error error) {
            return ResponseEntity.status(500).body("error getting all sessions");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSessionById(@PathVariable Long id) {
        try {
            List<?> show = sessionRepository.getProfessorsPresence();
            return ResponseEntity.ok((Session) sessionRepository.findById(id).orElse(null));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting user");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createSession(@Valid @RequestBody final Session sessionInfo) {
        try {
            final Session session = new Session();
            session.setGrade(sessionInfo.getGrade());
            session.setStartTime(sessionInfo.getStartTime());
            session.setDuration(sessionInfo.getDuration());
            session.setDateOfSession(sessionInfo.getDateOfSession());
            session.setProfessorPresence(sessionInfo.getProfessorPresence());
            Professor professor = professorRepository.findById(sessionInfo.getProfessor().getId()).orElse(null);
            if (professor != null) {
                session.setProfessor(professor);
                return ResponseEntity.ok(sessionRepository.save(session));
            } else {
                throw new Error("error adding the session, student not found");
            }
        } catch (final Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSession(@RequestBody Session sessionInfo, @PathVariable long id) {
        try {
            Session session = sessionRepository.findById(id).orElse(null);
            if (session != null) {
                sessionInfo.setId(id);
                sessionRepository.save(sessionInfo);
                return ResponseEntity.ok(sessionInfo);
            } else {
                throw new Error("session does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        try {
            Session session = sessionRepository.findById(id).orElse(null);
            if (session != null) {
                sessionRepository.delete(session);
                return ResponseEntity.ok("session deleted");
            } else {
                throw new Error("session does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }
}