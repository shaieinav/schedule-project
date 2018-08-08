package com.schedule_project.semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SemesterRepository extends JpaRepository<Semester, String> {

}