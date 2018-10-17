package com.schedule_project.semester;

import com.schedule_project.course_group.CourseGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;
    private final Logger log = LoggerFactory.getLogger(SemesterController.class);

    public List<Semester> getAllSemesters() {
        log.info("Request to get all semesters");
        return semesterRepository.findAll();
    }

    public Semester getSemester(String semesterName) throws Exception {
        log.info("Request to get semester: {}", semesterName);
        return semesterRepository.findById(semesterName)
                                 .orElseThrow(() -> new Exception("Can't find semester id"));
    }

    public List<CourseGroup> getSemesterCourseGroups(String semesterName) throws Exception {
        log.info("Request to get the semester course groups: {}", semesterName);
        return semesterRepository.findById(semesterName)
                                 .orElseThrow(() -> new Exception("Can't find semester id"))
                                 .getSemesterCourseGroups();
    }

    public void addSemester(Semester semester) {
        log.info("Request to create semester: {}", semester);
        semesterRepository.save(semester);
    }

    public void updateSemester(String semesterName, Semester semester) {
        log.info("Request to update semester: {}", semesterName);
        semesterRepository.save(semester);
    }

    public void deleteSemester(String semesterName) {
        log.info("Request to delete semester: {}", semesterName);
        semesterRepository.deleteById(semesterName);
    }
}