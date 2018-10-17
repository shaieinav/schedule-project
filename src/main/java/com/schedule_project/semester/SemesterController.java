package com.schedule_project.semester;

import com.schedule_project.course_group.CourseGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SemesterController {

    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/semesters")
    public List<Semester> getAllSemesters() {
        return semesterService.getAllSemesters();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/semesters/{semesterName}")
    public Semester getSemester(@PathVariable String semesterName) throws Exception {
        return semesterService.getSemester(semesterName);
    }

    @GetMapping("/semesters/{semesterName}/courseGroups")
    public List<CourseGroup> getSemesterCourseGroups(@PathVariable String semesterName) throws Exception {
        return semesterService.getSemesterCourseGroups(semesterName);
    }

    @PostMapping("/semesters")
    public void addSemester(@RequestBody Semester semester) {
        semesterService.addSemester(semester);
    }

    @PutMapping("/semesters/{semesterName}")
    public void updateSemester(@RequestBody Semester semester, @PathVariable String semesterName) {
        semesterService.updateSemester(semesterName, semester);
    }

    @DeleteMapping("/semesters/{semesterName}")
    public void deleteSemester(@PathVariable String semesterName) {
        semesterService.deleteSemester(semesterName);
    }
}