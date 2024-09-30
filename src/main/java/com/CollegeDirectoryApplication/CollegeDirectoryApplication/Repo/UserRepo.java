package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Role;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    List<User> findByFullnameContainingIgnoreCase(String name);
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);
}
