
package com.backendSOA.backendSOA.controllers;

import com.backendSOA.backendSOA.models.Agent;
import com.backendSOA.backendSOA.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {
    @Autowired
    private AgentRepository agentRepository;

    @GetMapping("/")
    public ResponseEntity<?> all() {
        try {
            return ResponseEntity.ok((List<Agent>) agentRepository.findAll());
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting all agents");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok((Agent) agentRepository.findById(id).orElse(null));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error getting agents");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createAgent(@Valid @RequestBody Agent AgentInfo) {
        try {
            Agent agent = new Agent();
            agent.setEmail(AgentInfo.getEmail());
            agent.setPassword(AgentInfo.getPassword());
            agent.setRole(AgentInfo.getRole());
            agent.setFirstName(AgentInfo.getFirstName());
            agent.setLastName(AgentInfo.getLastName());
            agent.setBirthDate(AgentInfo.getBirthDate());
            agent.setAgentRole(AgentInfo.getAgentRole());
            return ResponseEntity.ok(agentRepository.save(agent));
        } catch (Error error) {
            return ResponseEntity.status(500).body("error signing up");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAgent(@Valid @PathVariable Long id, @RequestBody Agent agentInfo) {
        try {
            Agent agent = agentRepository.findById(id).orElse(null);
            if (agent != null) {
                agentInfo.setId(id);
                return ResponseEntity.ok(agentRepository.save(agentInfo));
            } else {
                return ResponseEntity.ok(agentRepository.save(agentInfo));
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id) {
        try {
            Agent agent = agentRepository.findById(id).orElse(null);
            if (agent != null) {
                agentRepository.delete(agent);
                return ResponseEntity.ok("agent deleted");
            } else {
                throw new Error("agent does not exist");
            }
        } catch (Error error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }
}