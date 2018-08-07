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

    @RequestMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping("/courses/{courseNum}")
    public Course getCourse(@PathVariable Integer courseNum) throws Exception {
        return courseService.getCourse(courseNum);
    }

    @RequestMapping(method= RequestMethod.POST, value="/courses")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/courses/{courseNum}")
    public void updateCourse(@RequestBody Course course, @PathVariable Integer courseNum) {
        courseService.updateCourse(courseNum, course);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/courses/{courseNum}")
    public void deleteCourse(@PathVariable Integer courseNum) {
        courseService.deleteCourse(courseNum);
    }
}