package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Faculty;

import java.util.List;

public interface FacultyService {

    String create(Faculty faculty , long departmentId);
    String update(String id, long departmentId , Faculty faculty);
    Faculty getFaculty(String id);
    List<Faculty> getFaculties();
    String delete(String facultyId);
}
