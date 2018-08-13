package com.schedule_project.teaches;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.schedule_project.course.Course;
import com.schedule_project.instructor.Instructor;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teaches")
@EqualsAndHashCode
public class Teaches implements Serializable {

    @EmbeddedId
    private TeachesId teachesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "instructorJson")
//    @MapsId("studies_student_id")
    @JoinColumn(name = "teaches_instructor_id", insertable = false, updatable = false)
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "courseJson2")
//    @MapsId("studies_course_num")
    @JoinColumn(name = "teaches_course_num", insertable = false, updatable = false)
    private Course course;

    @Column(name = "teach_day")
    private String teachDay;

    @Column(name = "teach_time")
    private String teachTime;

    public Teaches() {

    }

    public Teaches(TeachesId teachesId, Instructor instructor, Course course, String teachDay, String teachTime) {
        this.teachesId = new TeachesId(instructor.getInstructorId(), course.getCourseNum());
        this.instructor = instructor;
        this.course = course;
        this.teachDay = teachDay;
        this.teachTime = teachTime;
    }

    public TeachesId getTeachesId() {
        return teachesId;
    }

    public void setTeachesId(TeachesId teachesId) {
        this.teachesId = teachesId;
    }

    @Transient
    public Integer getInstructor() {
        return instructor.getInstructorId();
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Transient
    public Integer getCourse() {
        return course.getCourseNum();
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTeachDay() {
        return teachDay;
    }

    public void setTeachDay(String teachDay) {
        this.teachDay = teachDay;
    }

    public String getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(String teachTime) {
        this.teachTime = teachTime;
    }

    @Override
    public String toString() {
        return "Teaches{" +
                "teachesId=" + teachesId +
                ", instructor=" + instructor +
                ", course=" + course +
                ", teachDay='" + teachDay + '\'' +
                ", teachTime=" + teachTime +
                '}';
    }
}