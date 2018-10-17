package com.schedule_project.course_group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGroupService {

    @Autowired
    private CourseGroupRepository courseGroupRepository;
    private final Logger log = LoggerFactory.getLogger(CourseGroupController.class);

    public List<CourseGroup> getAllCourseGroups() {
        log.info("Request to get all course groups");
        return courseGroupRepository.findAll();
    }
}