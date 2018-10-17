package com.schedule_project.instructor;

import com.schedule_project.teaches.Teaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    private final Logger log = LoggerFactory.getLogger(InstructorController.class);

    public List<Instructor> getAllInstructors() {
        log.info("Request to get all instructors");
        return instructorRepository.findAll();
    }

    public Instructor getInstructor(Integer instructorId) throws Exception {
        log.info("Request to get instructor: {}", instructorId);
        return instructorRepository.findById(instructorId)
                                   .orElseThrow(() -> new Exception("Can't find instructor id"));
    }

    public List<Teaches> getInstructorCourses(Integer instructorId) throws Exception {
        log.info("Request to get instructor courses: {}", instructorId);
        return instructorRepository.findById(instructorId)
                                   .orElseThrow(() -> new Exception("Can't find instructor id"))
                                   .getInstructorsCourses();
    }

    public void addInstructor(Instructor instructor) {
        log.info("Request to create instructor: {}", instructor);
        instructorRepository.save(instructor);
    }

    public void updateInstructor(Integer instructorId, Instructor instructor) {
        log.info("Request to update instructor: {}", instructorId);
        instructorRepository.save(instructor);
    }

    public void deleteInstructor(Integer instructorId) {
        log.info("Request to delete instructor: {}", instructorId);
        instructorRepository.deleteById(instructorId);
    }
}