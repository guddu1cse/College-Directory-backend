package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
}
