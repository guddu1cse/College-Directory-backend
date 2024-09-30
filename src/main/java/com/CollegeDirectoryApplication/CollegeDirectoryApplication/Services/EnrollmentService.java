package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    String create(Long courseId , String studentId);
    List<Enrollment> getEnrollments();
    Enrollment getEnrollmentById(long id);
    String deleteEnrollment(long enrollmentId);
    Enrollment getEnrollment(long id);
}
