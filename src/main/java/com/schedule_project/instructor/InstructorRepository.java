package com.schedule_project.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}