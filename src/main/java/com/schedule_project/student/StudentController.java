package com.schedule_project.student;

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

    @RequestMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping("/students/{studentId}")
    public Student getStudent(@PathVariable Integer studentId) throws Exception {
        return studentService.getStudent(studentId);
    }

    @RequestMapping(method= RequestMethod.POST, value="/students")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/students/{studentId}")
    public void updateStudent(@RequestBody Student student, @PathVariable Integer studentId) {
        studentService.updateStudent(studentId, student);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/students/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
    }
}