package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepo extends JpaRepository<Faculty , String> {

}
