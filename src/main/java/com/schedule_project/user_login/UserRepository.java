package com.schedule_project.user_login;

import com.schedule_project.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Student, String> {
}
