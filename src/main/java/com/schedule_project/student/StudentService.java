package com.schedule_project.student;

import com.schedule_project.enrolled.Enrolled;
import com.schedule_project.studies.Studies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    public List<Student> getAllStudents() {
        log.info("Request to get all student");
        return studentRepository.findAll();
    }

    public Student getStudent(Integer studentId) throws Exception {
        log.info("Request to get student: {}", studentId);
        return studentRepository.findById(studentId)
                                .orElseThrow(() -> new Exception("Can't find student id"));
    }

    public List<Studies> getStudentCourses(Integer studentId) throws Exception {
        log.info("Request to get the student courses: {}", studentId);
        return studentRepository.findById(studentId)
                                .orElseThrow(() -> new Exception("Can't find student id"))
                                .getStudentsCourses();
    }

    public List<Enrolled> getStudentCoursesGroups(Integer studentId) throws Exception {
        log.info("Request to get the student course groups: {}", studentId);
        return studentRepository.findById(studentId)
                                .orElseThrow(() -> new Exception("Can't find student id"))
                                .getStudentsCoursesGroups();
    }

    public void addStudent(Student student) {
        log.info("Request to create student: {}", student);
        studentRepository.save(student);
    }

    public void updateStudent(Integer studentId, Student student) {
        log.info("Request to update student: {}", studentId);
        studentRepository.save(student);
    }

    public void deleteStudent(Integer studentId) {
        log.info("Request to delete student: {}", studentId);
        studentRepository.deleteById(studentId);
    }
}