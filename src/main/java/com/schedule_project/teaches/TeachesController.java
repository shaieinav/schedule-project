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

//    The following methods are not implemented yet, need to see if they are even needed
//
//    @GetMapping("/teaches/{teachesId}")
//    public Teaches getTeaches(@PathVariable TeachesId teachesId) throws Exception {
//        return teachesService.getTeaches(teachesId);
//    }
//
//    @PostMapping("/teaches")
//    public void addTeaches(@RequestBody Teaches teaches) {
//        teachesService.addTeaches(teaches);
//    }
//
//    @PutMapping("/teaches/{teachesId}")
//    public void updateTeaches(@RequestBody Teaches teaches, @PathVariable TeachesId teachesId) {
//        teachesService.updateTeaches(teachesId, teaches);
//    }
}