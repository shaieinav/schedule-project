package com.schedule_project.studies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudiesService {

    @Autowired
    private StudiesRepository studiesRepository;
    private final Logger log = LoggerFactory.getLogger(StudiesController.class);

    public List<Studies> getAllStudies() {
        log.info("Request to get all students studies");
        return studiesRepository.findAll();
    }
}