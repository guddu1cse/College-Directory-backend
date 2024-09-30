package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Department;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Faculty;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Role;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.DepartmentRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.FacultyRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.UserRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImplementation implements FacultyService {

    @Autowired private FacultyRepo facultyRepo;
    @Autowired private DepartmentRepo departmentRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public String create(Faculty faculty , long departmentId) {
        try {
            Department department = departmentRepo.findById(departmentId).orElse(null);
            if(department == null) return "department is not present in database !";
            faculty.setDepartment(department);
            faculty.getUser().setRole(Role.FACULTY);
            faculty.getUser().setPassword(passwordEncoder.encode(faculty.getUser().getPassword()));
            facultyRepo.save(faculty);
            return "faculty created !";
        } catch(Exception e){
            return "failed to create faculty !\nReason: " + e.getMessage();
        }
    }

    @Override
    public String update(String id, long departmentId, Faculty faculty) {
        return "";
    }

    @Override
    public Faculty getFaculty(String id) {
        return facultyRepo.findById(id).orElse(null);
    }

    @Override
    public List<Faculty> getFaculties() {
        return facultyRepo.findAll();
    }

    @Override
    public String delete(String facultyId) {
        try {
            Faculty faculty = facultyRepo.findById(facultyId).orElse(null);
            if(faculty == null) return "faculty is not present for given id !";
            facultyRepo.delete(faculty);
            return "faculty deleted !";
        } catch (Exception e){
            return "failed to delete !\nReason: "+e.getMessage();
        }
    }
}
