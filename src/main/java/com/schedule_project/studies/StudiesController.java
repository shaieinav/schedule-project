package com.schedule_project.studies;

import com.schedule_project.course.Course;
import com.schedule_project.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudiesController {

    private final StudiesService studiesService;

    @Autowired
    public StudiesController(StudiesService studiesService) {
        this.studiesService = studiesService;
    }

    @RequestMapping("/studies")
    public List<Studies> getAllStudies() {
        return studiesService.getAllStudies();
    }

//    @RequestMapping("/studies/{studiesId}")
//    public Studies getStudies(@PathVariable Studies.StudiesId studiesId) throws Exception {
//        return studiesService.getStudies(studiesId);
//    }
    @RequestMapping("/studies/{courseNum}/{studentId}")
    public Studies getStudies(@PathVariable Course courseNum, @PathVariable Student studentId) throws Exception {
        return studiesService.getStudies(courseNum, studentId);
    }

    @RequestMapping(method= RequestMethod.POST, value="/studies")
    public void addStudies(@RequestBody Studies studies) {
        studiesService.addStudies(studies);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/studies/{studiesId}")
    public void updateStudies(@RequestBody Studies studies, @PathVariable Studies.StudiesId studiesId) {
        studiesService.updateStudies(studiesId, studies);
    }

}
