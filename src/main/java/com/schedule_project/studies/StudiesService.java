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

//    public Studies getStudies(Studies.StudiesId studiesId) throws Exception {
//        return studiesRepository.findById(studiesId).orElseThrow(() -> new Exception("Can't find studies id"));
//    }
    public Studies getStudies(Course courseNum, Student studentId) throws Exception {
        return getAllStudies().stream().filter(t -> t.getCourse().equals(courseNum) && t.getStudent().equals(studentId)).findFirst().get();
    }

    public void addStudies(Studies studies) {
        studiesRepository.save(studies);
    }

    public void updateStudies(Studies.StudiesId studiesId, Studies studies) {
        studiesRepository.save(studies);
    }
}
