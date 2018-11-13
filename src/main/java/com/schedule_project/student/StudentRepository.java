package com.schedule_project.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByUsernameOrEmail(String username, String email);

    List<Student> findByStudentIdIn(List<Long> studentIds);

    Optional<Student> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}