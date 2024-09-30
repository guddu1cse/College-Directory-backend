package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Course;

import java.util.List;

public interface CourseService {

    String createCourse(Course course , String facultyId , long departmentId);
    String updateCourse(long courseId , Course course ,String studentId , long departmentId );
    List<Course> getCourses();
    Course getCourseById(long id);
}
