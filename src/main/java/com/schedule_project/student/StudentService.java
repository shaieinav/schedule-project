package com.schedule_project.student;

import com.schedule_project.enrolled.Enrolled;
import com.schedule_project.studies.Studies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Integer studentId) throws Exception {
        return studentRepository.findById(studentId)
                                .orElseThrow(() -> new Exception("Can't find student id"));
    }

    public List<Studies> getStudentCourses(Integer studentId) throws Exception {
        return studentRepository.findById(studentId)
                                .orElseThrow(() -> new Exception("Can't find student id"))
                                .getStudentsCourses();
    }

    public List<Enrolled> getStudentCoursesGroups(Integer studentId) throws Exception {
        return studentRepository.findById(studentId)
                                .orElseThrow(() -> new Exception("Can't find student id"))
                                .getStudentsCoursesGroups();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer studentId, Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}