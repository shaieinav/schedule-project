package com.schedule_project.enrolled;

import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class EnrolledId implements Serializable {

    @Column(name = "enrolled_student_id", nullable = false)
    protected Long studentId;

    @Column(name = "enrolled_group_num", nullable = false)
    protected Integer groupNum;

    @Column(name = "enrolled_group_location", nullable = false)
    protected String groupLocation;

    @Column(name = "enrolled_course_num", nullable = false)
    protected Integer courseNum;

    @Column(name = "enrolled_semester_name", nullable = false)
    protected String semesterName;

    public EnrolledId() {

    }

    public EnrolledId(Long studentId, Integer groupNum, String groupLocation, Integer courseNum, String semesterName) {
        this.studentId = studentId;
        this.groupNum = groupNum;
        this.groupLocation = groupLocation;
        this.courseNum = courseNum;
        this.semesterName = semesterName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public String getGroupLocation() {
        return groupLocation;
    }

    public void setGroupLocation(String groupLocation) {
        this.groupLocation = groupLocation;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    @Override
    public String toString() {
        return "EnrolledId{" +
                "studentId=" + studentId +
                ", groupNum=" + groupNum +
                ", groupLocation=" + groupLocation +
                ", courseNum=" + courseNum +
                ", semesterName=" + semesterName +
                '}';
    }
}