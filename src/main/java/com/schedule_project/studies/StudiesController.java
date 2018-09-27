package com.schedule_project.studies;

import com.schedule_project.course.Course;
import com.schedule_project.student.Student;
import com.schedule_project.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
public class StudiesController {

    private final StudiesService studiesService;

    @Autowired
    public StudiesController(StudiesService studiesService) {
        this.studiesService = studiesService;
    }

    @GetMapping("/studies")
    public List<Studies> getAllStudies() {
        return studiesService.getAllStudies();
    }

//    The following methods are not implemented yet (trying to find a way to get only one studies by id),
//    need to see if they are even needed
//
//    @RequestMapping("/studies/{studiesId}")
//    public Enrolled getStudies(@PathVariable Enrolled.EnrolledId studiesId) throws Exception {
//        return studiesService.getStudies(studiesId);
//    }
//
//    @GetMapping("/studies/{courseNum}/{studentId}")
//    public Enrolled getStudies(@PathVariable Instructor courseNum, @PathVariable Semester studentId) throws Exception {
//        return studiesService.getStudies(courseNum, studentId);
//    }


//    The following methods are not implemented yet, need to see if they are even nedded
//
//    @GetMapping("/studies/{studiesId}")
//    public Studies getStudies(@PathVariable StudiesId studiesId) throws Exception {
//        return studiesService.getStudies(studiesId);
//    }
//
//    @PostMapping("/studies")
//    public void addStudies(@RequestBody Studies studies) {
//        studiesService.addStudies(studies);
//    }
//
//    @PutMapping("/studies/{studiesId}")
//    public void updateStudies(@RequestBody Studies studies, @PathVariable StudiesId studiesId) {
//        studiesService.updateStudies(studiesId, studies);
//    }
}