package com.schedule_project.teaches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeachesService {

    @Autowired
    private TeachesRepository teachesRepository;
    private final Logger log = LoggerFactory.getLogger(TeachesController.class);

    public List<Teaches> getAllTeaches() {
        log.info("Request to get all instructors teachings");
        return teachesRepository.findAll();
    }
}