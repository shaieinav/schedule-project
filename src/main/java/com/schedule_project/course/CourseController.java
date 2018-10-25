package com.schedule_project.course;

import com.schedule_project.course_group.CourseGroup;
import com.schedule_project.studies.Studies;
import com.schedule_project.teaches.Teaches;
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
    @GetMapping("/api/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/courses/{courseNum}")
    public Course getCourse(@PathVariable Integer courseNum) throws Exception {
        return courseService.getCourse(courseNum);
    }

    @GetMapping("/api/courses/{courseNum}/students")
    public List<Studies> getCourseStudents(@PathVariable Integer courseNum) throws Exception {
        return courseService.getCourseStudents(courseNum);
    }

    @GetMapping("/api/courses/{courseNum}/instructors")
    public List<Teaches> getCourseInstructors(@PathVariable Integer courseNum) throws Exception {
        return courseService.getCourseInstructors(courseNum);
    }

    @GetMapping("/api/courses/{courseNum}/courseGroups")
    public List<CourseGroup> getCourseGroups(@PathVariable Integer courseNum) throws Exception {
        return courseService.getCourseGroups(courseNum);
    }

    @PostMapping("/api/courses")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @PutMapping("/api/courses/{courseNum}")
    public void updateCourse(@RequestBody Course course, @PathVariable Integer courseNum) {
        courseService.updateCourse(courseNum, course);
    }

    @DeleteMapping("/api/courses/{courseNum}")
    public void deleteCourse(@PathVariable Integer courseNum) {
        courseService.deleteCourse(courseNum);
    }
}