package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Administrator;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Department;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Faculty;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Role;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.AdministratorRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.DepartmentRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.FacultyRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImplementation implements AdministratorService {
    @Autowired private FacultyRepo facultyRepo;
    @Autowired private DepartmentRepo departmentRepo;
    @Autowired private AdministratorRepo administratorRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public String create(Administrator administrator, long departmentId) {
        try {
            Department department = departmentRepo.findById(departmentId).orElse(null);
            if(department == null) return "department not present in database with given id !";
            administrator.setDepartment(department);
            administrator.getUser().setRole(Role.ADMINISTRATOR);
            administrator.getUser().setPassword(passwordEncoder.encode(administrator.getUser().getPassword()));
            administratorRepo.save(administrator);
            return "administrator created !";
        } catch (Exception e){
            return "failed to create administrator !\nReason: " + e.getMessage();
        }
    }

    @Override
    public String delete(String id) {
        try {
            Administrator administrator = administratorRepo.findById(id).orElse(null);
            if(administrator == null) return "administrator not present in database with given id !";
            administratorRepo.delete(administrator);
            return "administrator deleted !";
        } catch (Exception e){
            return "failed to delete administrator !\nReason: "+e.getMessage();
        }
    }

    @Override
    public String update(String adminId , Administrator faculty , long departmentId) {
        return "";
    }

    @Override
    public Faculty getFaculty(String id) {
        return facultyRepo.findById(id).orElse(null);
    }

    @Override
    public List<Faculty> faculties() {
        return facultyRepo.findAll();
    }

    @Override
    public Administrator getAdinById(String adminId) {
        return administratorRepo.findById(adminId).orElse(null);
    }

    @Override
    public List<Administrator> getAll() {
        return administratorRepo.findAll();
    }
}
