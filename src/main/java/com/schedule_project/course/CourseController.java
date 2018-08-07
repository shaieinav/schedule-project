package com.schedule_project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{courseNum}")
    public Course getCourse(@PathVariable Integer courseNum) throws Exception {
        return courseService.getCourse(courseNum);
    }

    @PostMapping("/courses")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @PutMapping("/courses/{courseNum}")
    public void updateCourse(@RequestBody Course course, @PathVariable Integer courseNum) {
        courseService.updateCourse(courseNum, course);
    }

    @DeleteMapping("/courses/{courseNum}")
    public void deleteCourse(@PathVariable Integer courseNum) {
        courseService.deleteCourse(courseNum);
    }
}