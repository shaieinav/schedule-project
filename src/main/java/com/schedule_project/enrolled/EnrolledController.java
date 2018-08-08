package com.schedule_project.enrolled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrolledController {

    private final EnrolledService enrolledService;

    @Autowired
    public EnrolledController(EnrolledService enrolledService) {
        this.enrolledService = enrolledService;
    }

    @GetMapping("/enrolled")
    public List<Enrolled> getAllEnrolls() {
        return enrolledService.getAllEnrolls();
    }

//    The following methods are not implemented yet, need to see if they are even needed
//
//    @GetMapping("/enrolled/{enrolledId}")
//    public Enrolled getEnrolled(@PathVariable EnrolledId enrolledId) throws Exception {
//        return enrolledService.getEnrolled(enrolledId);
//    }
//
//    @PostMapping("/enrolled")
//    public void addEnrolled(@RequestBody Enrolled enrolled) {
//        enrolledService.addEnrolled(enrolled);
//    }
//
//    @PutMapping("/enrolled/{enrolledId}")
//    public void updateEnrolled(@RequestBody Enrolled enrolled, @PathVariable EnrolledId enrolledId) {
//        enrolledService.updateEnrolled(enrolledId, enrolled);
//    }
}