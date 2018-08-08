package com.schedule_project.enrolled;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.schedule_project.course_group.CourseGroup;
import com.schedule_project.student.Student;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "enrolled")
@EqualsAndHashCode
public class Enrolled implements Serializable {

    @EmbeddedId
    private EnrolledId enrolledId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "studentJson2")
//    @MapsId("studies_student_id")
    @JoinColumn(name = "enrolled_student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "courseGroupJson")
//    @MapsId("studies_course_num")
    @JoinColumn(name = "enrolled_group_num", insertable = false, updatable = false)
    private CourseGroup courseGroup;

    public Enrolled() {

    }

    public Enrolled(Student student, CourseGroup courseGroup) {
        this.enrolledId = new EnrolledId(student.getStudentId(), courseGroup.getGroupNum, courseGroup.getGroupLocation,
                                         courseGroup.getCourseNum, courseGroup.getSemesterName);
        this.student = student;
        this.courseGroup = courseGroup;
    }

    public EnrolledId getEnrolledId() {
        return enrolledId;
    }

    public void setEnrolledId(EnrolledId enrolledId) {
        this.enrolledId = enrolledId;
    }

    @Transient
    public Integer getStudent() {
        return student.getStudentId();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Transient
    public Integer getCourseGroup() {
        return courseGroup.getCourseGroupName();
    }

    public void setCourseGroup(CourseGroup courseGroup) {
        this.courseGroup = courseGroup;
    }

    @Override
    public String toString() {
        return "Enrolled{" +
                "enrolledId=" + enrolledId +
                ", student=" + student +
//                ", courseGroup=" + courseGroup +
                '}';
    }
}