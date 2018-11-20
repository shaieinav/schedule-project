package com.schedule_project.studies;

import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class StudiesId implements Serializable {

    @Column(name = "studies_student_id", nullable = false)
    protected Long studentId;

    @Column(name = "studies_course_num", nullable = false)
    protected Integer courseNum;

    public StudiesId() {

    }

    public StudiesId(Long studentId, Integer courseNum) {
        this.studentId = studentId;
        this.courseNum = courseNum;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    @Override
    public String toString() {
        return "StudiesId{" +
                "studentId=" + studentId +
                ", courseNum=" + courseNum +
                '}';
    }
}