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

    @GetMapping("/studies")
    public List<Studies> getAllStudies() {
        return studiesService.getAllStudies();
    }
}