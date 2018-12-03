package com.schedule_project.enrolled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnrolledService {

    @Autowired
    private EnrolledRepository enrolledRepository;
    private final Logger log = LoggerFactory.getLogger(EnrolledController.class);

    public List<Enrolled> getAllEnrolls() {
        log.info("Request to get all enrollments");
        return enrolledRepository.findAll();
    }

    public Enrolled getEnrollment(EnrolledId enrolledId) throws Exception {
        log.info("Request to get enrollment: {}", enrolledId);
        return enrolledRepository.findById(enrolledId)
                .orElseThrow(() -> new Exception("Can't find enrolled id"));
    }

    public void addEnrollment(Enrolled enrolled) {
        log.info("Request to create enrollment: {}", enrolled);
        enrolledRepository.save(enrolled);
    }

//    public ResponseEntity<?> updateEnrollment(EnrolledId enrolledId/*, Enrolled enrolled*/, BindingResult result) throws Exception {
//        log.info("Request to update course: {}", enrolledId);
//
//        if (result.hasErrors()) {
//
//            Map<String, String> errorMap = new HashMap<>();
//
//            for (FieldError error: result.getFieldErrors()) {
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            }
//
//            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
//        }

//        Enrolled updatedEnrolled = enrolledRepository.findById(enrolledId)
//                .orElseThrow(() -> new Exception("Can't find enrolled id"));
//
//        log.info("updated enrolled: {}", updatedEnrolled);

//        EnrolledId updatedId = new EnrolledId();
//        updatedId.setCourseNum(enrolledId.getCourseNum());
//        updatedId.setGroupLocation(enrolledId.getGroupLocation());
//        updatedId.setGroupNum(enrolledId.getGroupNum());
//        updatedId.setSemesterName(enrolledId.getSemesterName());
//        updatedId.setStudentId(enrolledId.getStudentId());
//
//        Enrolled updatedEnrolled = new Enrolled();
//        updatedEnrolled.setEnrolledId(updatedId);
//
//        enrolledRepository.save(updatedEnrolled);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    public void deleteEnrollment(EnrolledId enrolledId) {
        log.info("Request to delete enrollment: {}", enrolledId);

        EnrolledId updatedId = new EnrolledId();
        updatedId.setCourseNum(enrolledId.getCourseNum());
        updatedId.setGroupLocation(enrolledId.getGroupLocation());
        updatedId.setGroupNum(enrolledId.getGroupNum());
        updatedId.setSemesterName(enrolledId.getSemesterName());
        updatedId.setStudentId(enrolledId.getStudentId());

        enrolledRepository.deleteById(updatedId);
    }
}