package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepo extends JpaRepository<Student , String> {

}
