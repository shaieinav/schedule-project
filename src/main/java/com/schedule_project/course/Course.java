package com.schedule_project.course;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schedule_project.course_group.CourseGroup;
import com.schedule_project.studies.Studies;
import com.schedule_project.teaches.Teaches;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "course")
@EqualsAndHashCode
public class Course {

    @Id
    @Column(name = "course_num", updatable = false, nullable = false)
    private Integer courseNum;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_credit")
    private Integer courseCredit;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "courseJson1")
    private List<Studies> studentsCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "courseJson2")
    private List<Teaches> instructorsCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "courseJson3")
    private List<CourseGroup> courseGroups = new ArrayList<>();

    public Course() {

    }

    public Course(Integer courseNum, String courseName, Integer courseCredit, List<Studies> studentsCourses, List<CourseGroup> courseGroups) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.studentsCourses = studentsCourses;
        this.courseGroups = courseGroups;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

    public List<Studies> getStudentsCourses() {
        return studentsCourses;
    }

    public void setStudentsCourses(List<Studies> studentsCourses) {
        this.studentsCourses = studentsCourses;
    }

    public List<Teaches> getInstructorsCourses() {
        return instructorsCourses;
    }

    public void setInstructorsCourses(List<Teaches> instructorsCourses) {
        this.instructorsCourses = instructorsCourses;
    }

    public List<CourseGroup> getCourseGroups() {
        return courseGroups;
    }

    public void setCourseGroups(List<CourseGroup> courseGroups) {
        this.courseGroups = courseGroups;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "courseNum=" + courseNum +
                ", courseName='" + courseName + '\'' +
                ", courseCredit=" + courseCredit +
                ", students=" + studentsCourses +
                '}';
    }
}