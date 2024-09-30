package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
   String createUser(User user);
   List<User> getUsers();
   Optional<User> getUserById(String id);
   String updateUser(String userid , User user);
   String delete(String id);
}
