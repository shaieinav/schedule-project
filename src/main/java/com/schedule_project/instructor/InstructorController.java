package com.schedule_project.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/instructors/{instructorId}")
    public Instructor getInstructor(@PathVariable Integer instructorId) throws Exception {
        return instructorService.getInstructor(instructorId);
    }

    @PostMapping("/instructors")
    public void addInstructor(@RequestBody Instructor instructor) {
        instructorService.addInstructor(instructor);
    }

    @PutMapping("/instructors/{instructorId}")
    public void updateInstructor(@RequestBody Instructor instructor, @PathVariable Integer instructorId) {
        instructorService.updateInstructor(instructorId, instructor);
    }

    @DeleteMapping("/instructors/{instructorId}")
    public void deleteInstructor(@PathVariable Integer instructorId) {
        instructorService.deleteInstructor(instructorId);
    }
}