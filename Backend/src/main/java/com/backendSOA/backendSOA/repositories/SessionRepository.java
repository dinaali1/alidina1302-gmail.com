package com.backendSOA.backendSOA.repositories;

import java.util.List;

import com.backendSOA.backendSOA.models.Session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    @Query("SELECT dateOfSession,isProfesseorPresent,professor from Session")
	List<?> getProfessorsPresence();
}