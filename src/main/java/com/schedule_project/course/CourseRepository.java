package com.schedule_project.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepository extends JpaRepository<Course, Integer> {

}