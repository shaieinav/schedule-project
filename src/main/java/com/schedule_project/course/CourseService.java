package com.schedule_project.course;

import com.schedule_project.course_group.CourseGroup;
import com.schedule_project.studies.Studies;
import com.schedule_project.teaches.Teaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    private final Logger log = LoggerFactory.getLogger(CourseController.class);

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum)
                               .orElseThrow(() -> new Exception("Can't find course id"));
    }

    public List<Studies> getCourseStudents(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum)
                               .orElseThrow(() -> new Exception("Can't find course id"))
                               .getStudentsCourses();
    }

    public List<Teaches> getCourseInstructors(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum)
                               .orElseThrow(() -> new Exception("Can't find course id"))
                               .getInstructorsCourses();
    }

    public List<CourseGroup> getCourseGroups(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum)
                               .orElseThrow(() -> new Exception("Can't find course id"))
                               .getCourseGroups();
    }

    public void addCourse(Course course) {
        log.info("Request to create course: {}", course);
        courseRepository.save(course);
    }

    public void updateCourse(Integer course_num, Course course) {
        log.info("Request to update course: {}", course);
        courseRepository.save(course);
    }

    public void deleteCourse(Integer courseNum) {
        log.info("Request to delete course: {}", courseNum);
        courseRepository.deleteById(courseNum);
    }

//    public List<Course> getCourseBySemester(String semesterName, Integer courseNum) {
//        return courseRepository.findCourse(semesterName, courseNum);
//    }
}