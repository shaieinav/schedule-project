package com.schedule_project.enrolled;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class EnrolledId implements Serializable {

    @Column(name = "enrolled_student_id")
    protected Integer studentId;

    @Column(name = "enrolled_group_num")
    protected Integer groupNum;

    @Column(name = "enrolled_group_location")
    protected String groupLocation;

    @Column(name = "enrolled_course_num")
    protected Integer courseNum;

    @Column(name = "enrolled_semester_name")
    protected String semesterName;

    public EnrolledId() {

    }

    public EnrolledId(Integer studentId, Integer groupNum, String groupLocation, Integer courseNum, String semesterName) {
        this.studentId = studentId;
        this.groupNum = groupNum;
        this.groupLocation = groupLocation;
        this.courseNum = courseNum;
        this.semesterName = semesterName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
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
