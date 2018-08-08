package com.schedule_project.studies;

import com.schedule_project.course.Course;
import com.schedule_project.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudiesService {

    @Autowired
    private StudiesRepository studiesRepository;

    public List<Studies> getAllStudies() {
        return studiesRepository.findAll();
    }

//    The following methods are not implemented yet (trying to find a way to get only one studies by id),
//    need to see if they are even needed
//
//    public Enrolled getStudies(Enrolled.EnrolledId studiesId) throws Exception {
//        return studiesRepository.findById(studiesId).orElseThrow(() -> new Exception("Can't find studies id"));
//    }
//
//    public Enrolled getStudies(Instructor courseNum, Semester studentId) throws Exception {
//        return getAllStudies().stream().filter(t -> t.getCourse().equals(courseNum) && t.getStudent().equals(studentId)).findFirst().get();
//    }

//    The following methods are not implemented yet, need to see if they are even needed
//
//    public Studies getStudies(StudiesId studiesId) throws Exception {
//        return studiesRepository.findById(studiesId).orElseThrow(() -> new Exception("Can't find studies id"));
//    }
//
//    public void addStudies(Studies studies) {
//        studiesRepository.save(studies);
//    }
//
//    public void updateStudies(StudiesId studiesId, Studies studies) {
//        studiesRepository.save(studies);
//    }
}