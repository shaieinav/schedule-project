package com.schedule_project.enrolled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrolledService {

    @Autowired
    private EnrolledRepository enrolledRepository;
    private final Logger log = LoggerFactory.getLogger(EnrolledController.class);

    public List<Enrolled> getAllEnrolls() {
        log.info("Request to get all enrollments");
        return enrolledRepository.findAll();
    }
}