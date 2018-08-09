package com.schedule_project.course_group;

import com.schedule_project.studies.Studies;
import com.schedule_project.teaches.Teaches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGroupService {

    @Autowired
    private CourseGroupRepository courseGroupRepository;

    public List<CourseGroup> getAllCourses() {
        return courseGroupRepository.findAll();
    }

    /*
    public CourseGroup getCourse(Integer courseNum) throws Exception {
        return courseGroupRepository.findById(courseNum).orElseThrow(() -> new Exception("Can't find course id"));
    }

    public List<Studies> getCourseStudents(Integer courseNum) throws Exception {
        return courseGroupRepository.findById(courseNum)
                                    .orElseThrow(() -> new Exception("Can't find course id"))
                                    .getStudentsCourses();
    }

    public List<Teaches> getCourseInstructors(Integer courseNum) throws Exception {
        return courseGroupRepository.findById(courseNum)
                                    .orElseThrow(() -> new Exception("Can't find course id"))
                                    .getInstructorsCourses();
    }

    public void addCourse(CourseGroup courseGroup) {
        courseGroupRepository.save(courseGroup);
    }

    public void updateCourse(Integer course_num, CourseGroup courseGroup) {
        courseGroupRepository.save(courseGroup);
    }

    public void deleteCourse(Integer courseNum) {
        courseGroupRepository.deleteById(courseNum);
    }
    */
}