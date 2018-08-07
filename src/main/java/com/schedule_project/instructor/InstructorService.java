package com.schedule_project.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructor(Integer instructorId) throws Exception {
        return instructorRepository.findById(instructorId).orElseThrow(() -> new Exception("Can't find instructor id"));
    }

    public void addInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public void updateInstructor(Integer instructorId, Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public void deleteInstructor(Integer instructorId) {
        instructorRepository.deleteById(instructorId);
    }
}