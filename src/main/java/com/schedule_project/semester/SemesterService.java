package com.schedule_project.semester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Semester getSemester(String semesterName) throws Exception {
        return semesterRepository.findById(semesterName)
                                 .orElseThrow(() -> new Exception("Can't find semester id"));
    }

    public void addSemester(Semester semester) {
        semesterRepository.save(semester);
    }

    public void updateSemester(String semesterName, Semester semester) {
        semesterRepository.save(semester);
    }

    public void deleteSemester(String semesterName) {
        semesterRepository.deleteById(semesterName);
    }
}