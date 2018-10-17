package com.schedule_project.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schedule_project.enrolled.Enrolled;
import com.schedule_project.studies.Studies;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "student")
@EqualsAndHashCode
public class Student {

    @Id
    @Column(name = "student_id", updatable = false, nullable = false)
    private Integer studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "studentJson1")
    private List<Studies> studentsCourses = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "studentJson2")
    private List<Enrolled> studentsCoursesGroups = new ArrayList<>();

    public Student() {

    }

    public Student(Integer studentId, String firstName, String lastName, String email, List<Studies> studentsCourses, List<Enrolled> studentsCoursesGroups) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentsCourses = studentsCourses;
        this.studentsCoursesGroups = studentsCoursesGroups;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Studies> getStudentsCourses() {
        return studentsCourses;
    }

    public List<Enrolled> getStudentsCoursesGroups() {
        return studentsCoursesGroups;
    }

    public void setStudentsCoursesGroups(List<Enrolled> studentsCoursesGroups) {
        this.studentsCoursesGroups = studentsCoursesGroups;
    }

    public void setStudentsCourses(List<Studies> studentsCourses) {
        this.studentsCourses = studentsCourses;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + studentsCourses +
                '}';
    }
}