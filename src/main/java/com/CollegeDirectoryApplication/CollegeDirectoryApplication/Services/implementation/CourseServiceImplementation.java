package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Course;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Department;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Faculty;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.CourseRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.DepartmentRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.FacultyRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {
    @Autowired private CourseRepo courseRepo;
    @Autowired private FacultyRepo facultyRepo;
    @Autowired private DepartmentRepo departmentRepo;

    @Override
    public String createCourse(Course course, String facultyId, long departmentId) {
        Faculty faculty = facultyRepo.findById(facultyId).orElse(null);
        Department department = departmentRepo.findById(departmentId).orElse(null);
        if(faculty == null) return "faculty is not present with given id !";
        if(department == null) return "department not present with given id !";
        try {
            course.setFaculty(faculty);
            course.setDepartment(department);
            courseRepo.save(course);
            return "course created";
        } catch (Exception e){
            return "failed to create course !\nReason: " + e.getMessage();
        }
    }

    @Override
    public String updateCourse(long courseId, Course course, String studentId, long departmentId) {

        return "";
    }

    @Override
    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(long id) {
        return courseRepo.findById(id).orElse(null);
    }
}
