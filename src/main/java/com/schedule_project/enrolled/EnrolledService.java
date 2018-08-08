package com.schedule_project.enrolled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolledService {

    @Autowired
    private EnrolledRepository enrolledRepository;

    public List<Enrolled> getAllEnrolls() {
        return enrolledRepository.findAll();
    }

//    The following methods are not implemented yet, need to see if they are even needed
//
//    public Enrolled getEnrolled(EnrolledId enrolledId) throws Exception {
//        return enrolledRepository.findById(enrolledId).orElseThrow(() -> new Exception("Can't find enrolled id"));
//    }
//
//    public void addEnrolled(Enrolled enrolled) {
//        enrolledRepository.save(enrolled);
//    }
//
//    public void updateEnrolled(EnrolledId enrolledId, Enrolled enrolled) {
//        enrolledRepository.save(enrolled);
//    }
}
