package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Department;

import java.util.List;

public interface DepartmentService {

    String create(Department department);
    String delete(Long id);
    String update(long id , Department department);
    Department getDepartment(Long id);
    List<Department> getDepartments();
}
