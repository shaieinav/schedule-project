package com.schedule_project.enrolled;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EnrolledRepository extends JpaRepository<Enrolled, EnrolledId> {

    List<Enrolled> findByEnrolledIdStudentId(Long studentId);
}
