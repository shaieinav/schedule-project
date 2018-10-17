package com.schedule_project.teaches;

import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class TeachesId implements Serializable {

    @Column(name = "teaches_instructor_id")
    protected Integer instructorId;

    @Column(name = "teaches_course_num")
    protected Integer courseNum;

    public TeachesId() {

    }

    public TeachesId(Integer instructorId, Integer courseNum) {
        this.instructorId = instructorId;
        this.courseNum = courseNum;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    @Override
    public String toString() {
        return "TeachesId{" +
                "instructorId=" + instructorId +
                ", courseNum=" + courseNum +
                '}';
    }
}