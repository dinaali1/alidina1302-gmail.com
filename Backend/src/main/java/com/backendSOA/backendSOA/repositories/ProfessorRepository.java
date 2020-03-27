package com.backendSOA.backendSOA.repositories;

import java.util.List;

import com.backendSOA.backendSOA.models.Professor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
    List<Professor> findBySubject(String subject);
}