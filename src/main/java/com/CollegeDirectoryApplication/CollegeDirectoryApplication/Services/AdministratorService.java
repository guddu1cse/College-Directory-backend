package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Administrator;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Faculty;

import java.util.List;

public interface AdministratorService {
    String create(Administrator administrator , long departmentId);
    String delete(String id);
    String update(String adminId , Administrator faculty , long departmentId);
    Faculty getFaculty(String id);
    List<Faculty> faculties();
    Administrator getAdinById(String adminId);
    List<Administrator> getAll();
}
