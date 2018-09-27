package com.schedule_project.studies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudiesRepository extends JpaRepository<Studies, StudiesId> {

}