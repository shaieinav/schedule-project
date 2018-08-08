package com.schedule_project.studies;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.schedule_project.course.Course;
import com.schedule_project.student.Student;
import lombok.*;

@Entity
@Table(name = "studies")
@EqualsAndHashCode
public class Studies implements Serializable {

    @EmbeddedId
    private StudiesId studiesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "studentJson1")
//    @MapsId("studies_student_id")
    @JoinColumn(name = "studies_student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "courseJson1")
//    @MapsId("studies_course_num")
    @JoinColumn(name = "studies_course_num", insertable = false, updatable = false)
    private Course course;

    @Column(name = "study_day")
    private String studyDay;

    @Column(name = "study_time")
    private Time studyTime;

    public Studies() {

    }

    public Studies(Course course, Student student, String studyDay, Time studyTime) {
        this.studiesId = new StudiesId(course.getCourseNum(), student.getStudentId());
        this.studyDay = studyDay;
        this.studyTime = studyTime;
        this.course = course;
        this.student = student;
    }

    public StudiesId getStudiesId() {
        return studiesId;
    }

    public void setStudiesId(StudiesId studiesId) {
        this.studiesId = studiesId;
    }

    @Transient
    public Integer getStudent() {
        return student.getStudentId();
    }

//    public Semester getStudentObj() {
//        return student;
//    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Transient
    public Integer getCourse() {
        return course.getCourseNum();
    }

//    public Instructor getCourseObj() {
//        return course;
//    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStudyDay() {
        return studyDay;
    }

    public void setStudyDay(String studyDay) {
        this.studyDay = studyDay;
    }

    public Time getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Time studyTime) {
        this.studyTime = studyTime;
    }

    @Override
    public String toString() {
        return "Enrolled{" +
                "studiesId={" +
                ", student=" + student.getStudentId() +
                ", course=" + course.getCourseNum() +
                "}" +
                ", studyDay='" + studyDay + '\'' +
                ", studyTime=" + studyTime +
                '}';
    }
}