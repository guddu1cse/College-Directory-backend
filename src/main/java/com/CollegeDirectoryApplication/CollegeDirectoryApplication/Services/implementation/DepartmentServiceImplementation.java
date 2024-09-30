package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Department;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.DepartmentRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImplementation implements DepartmentService {
    @Autowired private DepartmentRepo departmentRepo;
    @Override
    public String create(Department department) {
        try{
            departmentRepo.save(department);
            return "department created ! ";
        } catch(Exception e){
            return "Failed to create Department !\nReason: " + e.getMessage();
        }
    }

    @Override
    public String delete(Long id) {
        try {
            Department department = departmentRepo.findById(id).orElse(null);
            if(department == null) return "Department is not present with given id !";
            departmentRepo.delete(department);
            return "Department Deleted !";
        } catch (Exception e){
            return "failed to delete !\nReason: "+e.getMessage();
        }
    }

    @Override
    public String update(long id, Department department) {
        try{
            Department oldDepartment = departmentRepo.findById(id).orElse(null);

            if (oldDepartment == null) return "Department not present in database with given id !";
            if (department.getDescription() != null) oldDepartment.setDescription(department.getDescription());
            if (department.getName() != null) oldDepartment.setName(department.getName());
            departmentRepo.save(oldDepartment);
        } catch (Exception e){
            return "failed to update !\nReason: "+ e.getMessage();
        }
        return "Department updated !";
    }

    @Override
    public Department getDepartment(Long id) {
        return departmentRepo.findById(id).orElse(null);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepo.findAll();
    }
}
