package com.schedule_project.studies;

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

    @GetMapping("/studies/all")
    public List<Studies> getAllStudies() {
        return studiesService.getAllStudies();
    }

    @GetMapping("/studies")
    public Studies getStudies(@RequestBody StudiesId studiesId,
                                 @RequestParam Integer studentId, @RequestParam Integer courseNum) throws Exception {
        return studiesService.getStudies(studiesId);
    }

    @PostMapping("/studies")
    public void addStudies(@RequestBody Studies studies) {
        studiesService.addStudies(studies);
    }

    @DeleteMapping("/studies")
    public void deleteStudies(@RequestBody StudiesId studiesId) {
        studiesService.deleteStudies(studiesId);
    }
}