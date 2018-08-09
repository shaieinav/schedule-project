package com.schedule_project.student;

import com.schedule_project.enrolled.Enrolled;
import com.schedule_project.studies.Studies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable Integer studentId) throws Exception {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Studies> getStudentCourses(@PathVariable Integer studentId) throws Exception {
        return studentService.getStudentCourses(studentId);
    }

    @GetMapping("/students/{studentId}/courseGroups")
    public List<Enrolled> getStudentCoursesGroups(@PathVariable Integer studentId) throws Exception {
        return studentService.getStudentCoursesGroups(studentId);
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("/students/{studentId}")
    public void updateStudent(@RequestBody Student student, @PathVariable Integer studentId) {
        studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
    }
}