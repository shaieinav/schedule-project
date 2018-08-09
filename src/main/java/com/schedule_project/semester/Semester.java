package com.schedule_project.semester;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schedule_project.course_group.CourseGroup;
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

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "semesterJson")
    private List<CourseGroup> semesterCourseGroups = new ArrayList<>();

    public Semester() {

    }

    public Semester(String semesterName, String season, Date semesterStart, Date semesterEnd, List<CourseGroup> semesterCourseGroups) {
        this.semesterName = semesterName;
        this.season = season;
        this.semesterStart = semesterStart;
        this.semesterEnd = semesterEnd;
        this.semesterCourseGroups = semesterCourseGroups;
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

    public List<CourseGroup> getSemesterCourseGroups() {
        return semesterCourseGroups;
    }

    public void setSemesterCourseGroups(List<CourseGroup> semesterCourseGroups) {
        this.semesterCourseGroups = semesterCourseGroups;
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