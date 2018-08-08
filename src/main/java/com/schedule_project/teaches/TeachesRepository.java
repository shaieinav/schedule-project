package com.schedule_project.teaches;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TeachesRepository extends JpaRepository<Teaches, TeachesId> {

}