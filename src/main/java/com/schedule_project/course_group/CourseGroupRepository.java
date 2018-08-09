package com.schedule_project.course_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CourseGroupRepository extends JpaRepository<CourseGroup, CourseGroupId> {

}