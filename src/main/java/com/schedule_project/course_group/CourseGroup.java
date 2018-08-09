package com.schedule_project.course_group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.schedule_project.course.Course;
import com.schedule_project.enrolled.Enrolled;
import com.schedule_project.semester.Semester;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course_group")
@EqualsAndHashCode
public class CourseGroup {

    @EmbeddedId
    private CourseGroupId courseGroupId;

    @Column(name = "teaching_type")
    private String teachingType;

    @Column(name = "hours")
    private Time hours;

    @Column(name = "course_group_instructor_id")
    private Integer instructorId;

    @Column(name = "instructor_rating")
    private Integer instructorRating;

    @OneToMany(mappedBy = "courseGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "courseGroupJson1")
    private List<Enrolled> studentsCoursesGroups = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "courseJson3")
//    @MapsId("studies_student_id")
    @JoinColumn(name = "course_group_course_num", insertable = false, updatable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "semesterJson")
//    @MapsId("studies_student_id")
    @JoinColumn(name = "course_group_semester_name", insertable = false, updatable = false)
    private Semester semester;

    public CourseGroup() {

    }

    public CourseGroup(CourseGroupId courseGroupId, String teachingType, Time hours, Integer instructorId,
                       Integer instructorRating, List<Enrolled> studentsCoursesGroups, Course course, Semester semester) {
        this.courseGroupId = courseGroupId;
        this.teachingType = teachingType;
        this.hours = hours;
        this.instructorId = instructorId;
        this.instructorRating = instructorRating;
        this.studentsCoursesGroups = studentsCoursesGroups;
        this.course = course;
        this.semester = semester;
    }

    public CourseGroupId getCourseGroupId() {
        return courseGroupId;
    }

    public void setCourseGroupId(CourseGroupId courseGroupId) {
        this.courseGroupId = courseGroupId;
    }

    public String getTeachingType() {
        return teachingType;
    }

    public void setTeachingType(String teachingType) {
        this.teachingType = teachingType;
    }

    public Time getHours() {
        return hours;
    }

    public void setHours(Time hours) {
        this.hours = hours;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Integer getInstructorRating() {
        return instructorRating;
    }

    public void setInstructorRating(Integer instructorRating) {
        this.instructorRating = instructorRating;
    }

    public List<Enrolled> getStudentsCoursesGroups() {
        return studentsCoursesGroups;
    }

    public void setStudentsCoursesGroups(List<Enrolled> studentsCoursesGroups) {
        this.studentsCoursesGroups = studentsCoursesGroups;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "CourseGroup{" +
                "courseGroupId=" + courseGroupId +
                ", teachingType='" + teachingType + '\'' +
                ", hours='" + hours + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", instructorRating='" + instructorRating + '\'' +
                ", studentsCoursesGroups=" + studentsCoursesGroups +
                '}';
    }
}