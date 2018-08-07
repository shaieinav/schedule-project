package com.schedule_project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.addAll(courseRepository.findAll());
        return courses;
    }

    public Course getCourse(Integer courseNum) throws Exception {
        return courseRepository.findById(courseNum).orElseThrow(() -> new Exception("Can't find course id"));
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