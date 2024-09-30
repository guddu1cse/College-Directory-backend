package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department , Long> {
}
