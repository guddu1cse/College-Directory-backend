package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Role;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.User;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.UserRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired private UserRepo userRepo;

    @Override
    public String createUser(User user) {
        try {
            userRepo.save(user);
            return "user created !";
        } catch (Exception e){
            return "failed to create user";
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findByUsername(id);
    }

    @Override
    public String updateUser(String userid, User user) {
        User oldUser = userRepo.findById(userid).orElse(null);
        if(oldUser == null) return "user or student not present with given id !";
        user.setUsername(userid);
        userRepo.save(user);
        return "user updated";
    }

    @Override
    public String delete(String id) {
        try {
            User user = userRepo.findById(id).orElse(null);
            if(user == null) return "user is not present with given id";
            if(!user.getRole().toString().equals(Role.NONE.toString())) return "you can delete role profile";
            userRepo.delete(user);
            return id + "user deleted !";
        } catch (Exception e){
            return "failed to delete !\nReason: "+e.getMessage();
        }
    }
}
