package com.schedule_project.enrolled;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EnrolledRepository extends JpaRepository<Enrolled, EnrolledId> {

}
