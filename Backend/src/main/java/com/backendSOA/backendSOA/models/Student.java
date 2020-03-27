package com.backendSOA.backendSOA.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {

    @Column(name = "numero_carte_etudiant")
    private Long numCarteEtudiant;

    @Column(name = "grade")
    private String grade;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Promotion> promotions = new ArrayList<>();

    public Long getNumCarteEtudiant() {
        return this.numCarteEtudiant;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setNumCarteEtudiant(Long numCarteEtudiant) {
        this.numCarteEtudiant = numCarteEtudiant;
    }

}