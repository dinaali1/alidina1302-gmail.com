package com.backendSOA.backendSOA.models;

import javax.persistence.*;

@Entity
@Table(name = "agents")
public class Agent extends User {
    
    @Column(name = "agent_role")
    private String agentRole;

    public String getAgentRole() {
        return this.agentRole;
    }

    public void setAgentRole(String agentRole) {
        this.agentRole = agentRole;
    }

}