package com.schedule_project.course_group;

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
    public List<CourseGroup> getAllCourseGroups() {
        return courseGroupService.getAllCourseGroups();
    }
}