package com.schedule_project.course;

import com.schedule_project.studies.Studies;
import com.schedule_project.teaches.Teaches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum).orElseThrow(() -> new Exception("Can't find course id"));
    }

    public List<Studies> getCourseStudents(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum).orElseThrow(() -> new Exception("Can't find course id")).getStudentsCourses();
    }

    public List<Teaches> getCourseInstructors(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum).orElseThrow(() -> new Exception("Can't find course id")).getInstructorsCourses();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Integer course_num, Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Integer courseNum) {
        courseRepository.deleteById(courseNum);
    }
}