package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Course;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Faculty;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    String create(Student student , long departmentId);
    Student getById(String id);
    List<Student> getStudents();
    String delete(String id);
    List<Course> getCourse(String id);
    List<Student> searchByNameLike(String keyword);
    List<Faculty> getFaculty(String id);
    List<Student> getStudentByFacultyId(String facultyId);
    String updateStudent(Student newstudent , String studentId, long departmentId);
    HashMap<String , Integer> getDepartmentAndStudents();
}
