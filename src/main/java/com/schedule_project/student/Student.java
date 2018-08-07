package com.schedule_project.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schedule_project.course.Course;
import com.schedule_project.studies.Studies;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.sql.Time;
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
    @JsonManagedReference(value = "studentJson")
    private List<Studies> studentsCourses = new ArrayList<>();

    public Student() {

    }

    public Student(Integer studentId, String firstName, String lastName, String email, List<Studies> studentsCourses) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentsCourses = studentsCourses;
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

    public void setStudentsCourses(List<Studies> studentsCourses) {
        this.studentsCourses = studentsCourses;
    }

//    public void addCourse(Course course, String studyDay, Time studyTime) {
//        Studies studies = new Studies(course, this, studyDay, studyTime);
//        courses.add(studies);
//        course.getStudents().add(studies);
//    }
//
//    public void removeCourse(Course course) {
//        for (Iterator<Studies> iterator = courses.iterator(); iterator.hasNext(); ) {
//
//            Studies studies = iterator.next();
//
//            if (studies.getStudentObj().equals(this) && studies.getCourseObj().equals(course)) {
//                iterator.remove();
//                studies.getCourseObj().getStudents().remove(studies);
//                studies.setStudent(null);
//                studies.setCourse(null);
//            }
//        }
//    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + studentsCourses +
                '}';
    }
}