package com.schedule_project.course_group;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class CourseGroupId implements Serializable {

    @Column(name = "group_num")
    protected Integer groupNum;

    @Column(name = "group_location")
    protected String groupLocation;

    @Column(name = "course_group_course_num")
    protected Integer courseNum;

    @Column(name = "course_group_semester_name")
    protected String semesterName;

    public CourseGroupId() {
    }

    public CourseGroupId(Integer groupNum, String groupLocation, Integer courseNum, String semesterName) {
        this.groupNum = groupNum;
        this.groupLocation = groupLocation;
        this.courseNum = courseNum;
        this.semesterName = semesterName;
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

    public void setGroupLocation(String group_location) {
        this.groupLocation = group_location;
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
        return "CourseGroupId{" +
                "groupNum=" + groupNum +
                ", group_location='" + groupLocation + '\'' +
                ", courseNum=" + courseNum +
                ", semesterName=" + semesterName +
                '}';
    }
}
