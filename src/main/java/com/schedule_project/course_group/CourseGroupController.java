package com.schedule_project.course_group;

import com.schedule_project.studies.Studies;
import com.schedule_project.teaches.Teaches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseGroupController {

    private final CourseGroupService courseGroupService;

    @Autowired
    public CourseGroupController(CourseGroupService courseGroupService) {
        this.courseGroupService = courseGroupService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/courseGroups")
    public List<CourseGroup> getAllCourses() {
        return courseGroupService.getAllCourses();
    }

    /*
    @GetMapping("/courses/{courseNum}")
    public CourseGroup getCourse(@PathVariable Integer courseNum) throws Exception {
        return courseGroupService.getCourse(courseNum);
    }

    @GetMapping("/courses/{courseNum}/students")
    public List<Studies> getCourseStudents(@PathVariable Integer courseNum) throws Exception {
        return courseGroupService.getCourseStudents(courseNum);
    }

    @GetMapping("/courses/{courseNum}/instructors")
    public List<Teaches> getCourseInstructors(@PathVariable Integer courseNum) throws Exception {
        return courseGroupService.getCourseInstructors(courseNum);
    }

    @PostMapping("/courses")
    public void addCourse(@RequestBody CourseGroup courseGroup) {
        courseGroupService.addCourse(courseGroup);
    }

    @PutMapping("/courses/{courseNum}")
    public void updateCourse(@RequestBody CourseGroup courseGroup, @PathVariable Integer courseNum) {
        courseGroupService.updateCourse(courseNum, courseGroup);
    }

    @DeleteMapping("/courses/{courseNum}")
    public void deleteCourse(@PathVariable Integer courseNum) {
        courseGroupService.deleteCourse(courseNum);
    }
    */
}