package com.backendSOA.backendSOA.models;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Column(name = "grade")
	private String grade;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "date")
    private Date dateOfSession;

    @Column(name = "professor_presence")
    private int isProfesseorPresent;

    public Long getSessionId() {
        return this.sessionId;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getDuration() {
        return this.duration;
    }

    public Date getDateOfSession() {
        return this.dateOfSession;
    }

    public String getGrade() {
        return this.grade;
    }

    public int getProfessorPresence() {
        return this.isProfesseorPresent;
    }

    public void setId(Long id) {
        this.sessionId = id;
    }

    public void setProfessor(Professor professor) {
        this.professor= professor;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
    
    public void setDateOfSession(Date dateOfSession) {
        this.dateOfSession = dateOfSession;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setProfessorPresence(int presence) {
        this.isProfesseorPresent = presence;
    }

}