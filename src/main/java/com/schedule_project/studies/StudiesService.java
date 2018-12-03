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

    public Studies getStudies(StudiesId studiesId) throws Exception {
        log.info("Request to get studies: {}", studiesId);
        return studiesRepository.findById(studiesId)
                .orElseThrow(() -> new Exception("Can't find studies id"));
    }

    public void addStudies(Studies studies) {
        log.info("Request to create studies: {}", studies);
        studiesRepository.save(studies);
    }

    public void deleteStudies(StudiesId studiesId) {

        log.info("Request to delete studies: {}", studiesId);

        StudiesId updatedId = new StudiesId();
        updatedId.setCourseNum(studiesId.getCourseNum());
        updatedId.setStudentId(studiesId.getStudentId());

        studiesRepository.deleteById(updatedId);
    }
}