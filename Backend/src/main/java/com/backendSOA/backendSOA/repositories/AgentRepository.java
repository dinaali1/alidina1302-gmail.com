package com.backendSOA.backendSOA.repositories;

import com.backendSOA.backendSOA.models.Agent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {
}