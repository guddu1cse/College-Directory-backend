package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.*;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.UserRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.AdministratorService;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.FacultyService;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.SearchService;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImplementation implements SearchService {
    @Autowired private UserRepo userRepo;
    @Autowired private AdministratorService administratorService;
    @Autowired private StudentService studentService;
    @Autowired private FacultyService facultyService;

    //searching handle base on role
    // admin can search for all student , faculty and admin
    //student search only for student
    //faculty search for student and faculty

    @Override
    public List<Search> getSearchAcrossRole(String keyword , String username) {
       Enum<Role> role = userRepo.findByUsername(username).orElse(null).getRole();

       if(role.equals(Role.STUDENT)) return getStudents(keyword);
       else if(role.equals(Role.FACULTY)) return getFaculties(keyword);
       return getAllUser(keyword);
    }

    private List<Search> getAllUser(String keyword) {
        List<User> users = userRepo.findByFullnameContainingIgnoreCase(keyword);
        List<Search> searches= new ArrayList<>();

        users.forEach(user -> {
                if(user.getRole().equals(Role.STUDENT)){
                    Student student = studentService.getById(user.getUsername());
                    if(student!=null) searches.add(studentToSearch(student));
                }else if(user.getRole().equals(Role.FACULTY)){
                    Faculty faculty = facultyService.getFaculty(user.getUsername());
                    if(faculty!=null) searches.add(facultyToSearch(faculty));
                } else if(user.getRole().equals(Role.ADMINISTRATOR)){
                    Administrator administrator = administratorService.getAdinById(user.getUsername());
                    if(administrator != null) searches.add(adminToSearch(administrator));
                }
        });

        return searches;
    }

    private List<Search> getFaculties(String keyword) {
        List<User> users = userRepo.findByFullnameContainingIgnoreCase(keyword);
        List<Search> searches= new ArrayList<>();

        users.forEach(user -> {
            if(user.getRole().equals(Role.FACULTY)){
                Faculty faculty = facultyService.getFaculty(user.getUsername());
                if(faculty!=null){
                    searches.add(facultyToSearch(faculty));
                }
            }
        });

        return searches;
    }

    private List<Search> getStudents(String keyword) {

        List<User> users = userRepo.findByFullnameContainingIgnoreCase(keyword);
        List<Search> searches= new ArrayList<>();

        users.forEach(user -> {
            if(user.getRole().equals(Role.STUDENT)){
                Student student = studentService.getById(user.getUsername());
                if(student!=null){
                    searches.add(studentToSearch(student));
                }
            }
        });

        return searches;
    }

    Search studentToSearch(Student source){
        Search search = new Search();
        search.setUsername(source.getUser().getUsername());
        search.setEmail(source.getUser().getEmail());
        search.setRole(source.getUser().getRole().toString());
        search.setContact(source.getUser().getContact());
        search.setPhotourl(source.getPhotourl());
        search.setDepartment(source.getDepartment().getName());
        search.setName(source.getUser().getFullname());

        return search;
    }

    Search facultyToSearch(Faculty source){
        Search search = new Search();
        search.setUsername(source.getUser().getUsername());
        search.setEmail(source.getUser().getEmail());
        search.setRole(source.getUser().getRole().toString());
        search.setContact(source.getUser().getContact());
        search.setPhotourl(source.getPhotourl());
        search.setDepartment(source.getDepartment().getName());
        search.setName(source.getUser().getFullname());

        return search;
    }

    Search adminToSearch(Administrator source){
        Search search = new Search();
        search.setUsername(source.getUser().getUsername());
        search.setEmail(source.getUser().getEmail());
        search.setRole(source.getUser().getRole().toString());
        search.setContact(source.getUser().getContact());
        search.setPhotourl(source.getPhotourl());
        search.setDepartment(source.getDepartment().getName());
        search.setName(source.getUser().getFullname());

        return search;
    }

}
