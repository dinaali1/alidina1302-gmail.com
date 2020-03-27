package com.backendSOA.backendSOA.repositories;

import java.util.List;

import com.backendSOA.backendSOA.models.Promotion;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long> {
	@Query("SELECT grade,student,year from Promotion GROUP BY year")
	List<?> fetchGrades();
	
	List<Promotion> findByYearAndGrade( String year,String grade);

}