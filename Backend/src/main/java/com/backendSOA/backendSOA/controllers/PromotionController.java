
package com.backendSOA.backendSOA.controllers;

import com.backendSOA.backendSOA.models.Promotion;
import com.backendSOA.backendSOA.models.Student;
import com.backendSOA.backendSOA.repositories.PromotionRepository;
import com.backendSOA.backendSOA.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/promotions")
public class PromotionController {
  @Autowired
  private PromotionRepository promotionRepository;

  @Autowired
  private StudentRepository studentRepository;

  @GetMapping("/")
  public ResponseEntity<?> all(@RequestParam(name = "grade") String grade, @RequestParam(name = "year") String year) {
    try {
      List<?> list = promotionRepository.fetchGrades();
      List<Promotion> list2 = promotionRepository.findByYearAndGrade(year, grade);
      return ResponseEntity.ok(list2);
    } catch (final Error error) {
      return ResponseEntity.status(500).body("error getting all promotions");
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPromotionById(@PathVariable Long id) {
    try {
      List<?> list = promotionRepository.fetchGrades();
      return ResponseEntity.ok((Promotion) promotionRepository.findById(id).orElse(null));
    } catch (Error error) {
      return ResponseEntity.status(500).body("error getting user");
    }
  }

  @PostMapping("/")
  public ResponseEntity<?> createPromotion(@Valid @RequestBody final Promotion promotionInfo) {
    try {
      final Promotion promotion = new Promotion();
      promotion.setYear(promotionInfo.getYear());
      promotion.setGrade(promotionInfo.getGrade());
      promotion.setResult(promotionInfo.getResult());
      Student student = studentRepository.findById(promotionInfo.getStudent().getId()).orElse(null);
      if (student != null) {
        promotion.setStudent(student);
        return ResponseEntity.ok(promotionRepository.save(promotion));
      } else {
        throw new Error("error adding the promotion, student not found");
      }
    } catch (final Error error) {
      return ResponseEntity.status(500).body(error.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updatePromotion(@RequestBody Promotion promotionInfo, @PathVariable long id) {
    try {
      Promotion promotion = promotionRepository.findById(id).orElse(null);
      if (promotion != null) {
        promotionInfo.setPromotionId(id);
        promotionRepository.save(promotionInfo);
        return ResponseEntity.ok(promotionInfo);
      } else {
        throw new Error("Promotion does not exist");
      }
    } catch (Error error) {
      return ResponseEntity.status(500).body(error.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePromotion(@PathVariable Long id) {
    try {
      Promotion promotion = promotionRepository.findById(id).orElse(null);
      if (promotion != null) {
        promotionRepository.delete(promotion);
        return ResponseEntity.ok("Promotion deleted");
      } else {
        throw new Error("Promotion does not exist");
      }
    } catch (Error error) {
      return ResponseEntity.status(500).body(error.getMessage());
    }
  }

}