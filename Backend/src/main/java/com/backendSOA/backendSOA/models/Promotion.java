package com.backendSOA.backendSOA.models;

import javax.persistence.*;

@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long promotionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "year")
    private String year;

    @Column(name = "grade")
    private String grade;

    @Column(name = "result")
    private int result;

    public Long getId() {
        return this.promotionId;
    }

    public Student getStudent() {
        return this.student;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getYear() {
        return this.year;
    }

    public int getResult() {
        return this.result;
    }

    public void setPromotionId(Long id) {
        this.promotionId = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}