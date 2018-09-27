package com.schedule_project.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseRepository extends JpaRepository<Course, Integer> {

//    @Query("SELECT Course.courseNum, Course.courseName, Course.courseCredit, CourseGroup.courseGroupId.groupLocation, CourseGroup.day, CourseGroup.hours, CourseGroup.courseGroupId.semesterName " +
//           "FROM Course JOIN CourseGroup ON Course.courseNum = courseNum " +
//           "WHERE CourseGroup.courseGroupId.semesterName = semesterName")
//    List<Course> findCourse(@Param("semester") String semesterName, @Param("course") Integer courseNum);

//    List<Course> findAllByStudentId(String id);
}