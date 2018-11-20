package com.schedule_project.student;

import com.schedule_project.enrolled.Enrolled;
import com.schedule_project.exception.ResourceNotFoundException;
import com.schedule_project.security.CurrentUser;
import com.schedule_project.security.UserPrincipal;
import com.schedule_project.studies.Studies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public Student getCurrentUser(@CurrentUser UserPrincipal userPrincipal) throws ResourceNotFoundException {
        return studentService.getCurrentUser(userPrincipal);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable Long studentId) throws Exception {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Studies> getStudentCourses(@PathVariable Long studentId) throws Exception {
        return studentService.getStudentCourses(studentId);
    }

    @GetMapping("/students/{studentId}/courseGroups")
    public List<Enrolled> getStudentCoursesGroups(@PathVariable Long studentId) throws Exception {
        return studentService.getStudentCoursesGroups(studentId);
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("/students/{studentId}")
    public void updateStudent(@RequestBody Student student, @PathVariable Long studentId) {
        studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }
}