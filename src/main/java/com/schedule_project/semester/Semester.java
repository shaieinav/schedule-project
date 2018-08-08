package com.schedule_project.semester;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semester")
@EqualsAndHashCode
public class Semester {

    @Id
    @Column(name = "semester_name", updatable = false, nullable = false)
    private String semesterName;

    @Column(name = "season")
    private String season;

    @Column(name = "semester_start")
    private Date semesterStart;

    @Column(name = "semester_end")
    private Date semesterEnd;

//    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference(value = "semesterJson")
//    private List<Enrolled> studentsCourses = new ArrayList<>();

    public Semester() {

    }

    public Semester(String semesterName, String season, Date semesterStart, Date semesterEnd) {
        this.semesterName = semesterName;
        this.season = season;
        this.semesterStart = semesterStart;
        this.semesterEnd = semesterEnd;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Date getSemesterStart() {
        return semesterStart;
    }

    public void setSemesterStart(Date semesterStart) {
        this.semesterStart = semesterStart;
    }

    public Date getSemesterEnd() {
        return semesterEnd;
    }

    public void setSemesterEnd(Date semesterEnd) {
        this.semesterEnd = semesterEnd;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "semesterName='" + semesterName + '\'' +
                ", season='" + season + '\'' +
                ", semesterStart=" + semesterStart +
                ", semesterEnd=" + semesterEnd +
                '}';
    }
}