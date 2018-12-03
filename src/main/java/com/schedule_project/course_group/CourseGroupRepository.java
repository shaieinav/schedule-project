package com.schedule_project.course_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseGroupRepository extends JpaRepository<CourseGroup, CourseGroupId> {

//    @Query("SELECT DISTINCT * FROM CourseGroup AS cg JOIN Student AS s ON cg.studentsCoursesGroups.enrolledId.studentId = s.studentId")
//    List<CourseGroup> findByStudentId(CourseGroupId);

}