package com.schedule_project.teaches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TeachesController {

    private final TeachesService teachesService;

    @Autowired
    public TeachesController(TeachesService teachesService) {
        this.teachesService = teachesService;
    }

    @GetMapping("/teaches")
    public List<Teaches> getAllTeaches() {
        return teachesService.getAllTeaches();
    }
}