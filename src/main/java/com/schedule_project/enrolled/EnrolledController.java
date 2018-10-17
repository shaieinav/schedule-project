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
}