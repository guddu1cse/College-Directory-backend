package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Controller;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.*;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.AdministratorService;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.FacultyService;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.StudentService;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class CurrentUserController {

    @Autowired private UserService userService;
    @Autowired private StudentService studentService;
    @Autowired private FacultyService facultyService;
    @Autowired private AdministratorService administratorService;


    @GetMapping("/current-user")
    public ResponseEntity<DashboardResponse> getCurrentUser(Principal principal) {
        User user = userService.getUserById(principal.getName()).orElse(null);
        String role = user.getRole().toString();
        if(role.equals(Role.STUDENT.toString())) return getStudentDash(user.getUsername());
        else if(role.equals(Role.FACULTY.toString())) return getFacultyDash(user.getUsername());
        return getAdminDash(user.getUsername());
    }

    private ResponseEntity<DashboardResponse> getAdminDash(String username) {
        Administrator admin = administratorService.getAdinById(username);
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setUsername(admin.getId());
        dashboardResponse.setName(admin.getUser().getFullname());
        dashboardResponse.setEmail(admin.getUser().getEmail());
        dashboardResponse.setRole(admin.getUser().getRole().toString());
        dashboardResponse.setPhotourl(admin.getPhotourl());
        dashboardResponse.setDepartment(admin.getDepartment().getName());
        dashboardResponse.setContact(admin.getUser().getContact());
        return new ResponseEntity<>(dashboardResponse , HttpStatus.OK);
    }

    private ResponseEntity<DashboardResponse> getStudentDash(String username) {
        Student student = studentService.getById(username);
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setUsername(student.getId());
        dashboardResponse.setName(student.getUser().getFullname());
        dashboardResponse.setEmail(student.getUser().getEmail());
        dashboardResponse.setRole(student.getUser().getRole().toString());
        dashboardResponse.setPhotourl(student.getPhotourl());
        dashboardResponse.setDepartment(student.getDepartment().getName());
        dashboardResponse.setContact(student.getUser().getContact());
        dashboardResponse.setSessionStart(student.getSessionStart().getDate()+ "/"
                + student.getSessionStart().getMonth() +"/"
                + (student.getSessionStart().getYear()+1900));
        dashboardResponse.setSessionEnd(student.getSessionEnd().getDate()+ "/"
                + student.getSessionEnd().getMonth() +"/"
                + (student.getSessionEnd().getYear()+1900));
        dashboardResponse.setYear(dashboardResponse.getSessionStart() +"-" + dashboardResponse.getSessionEnd());

        return new ResponseEntity<>(dashboardResponse , HttpStatus.OK);
    }

    private ResponseEntity<DashboardResponse> getFacultyDash(String username) {
        Faculty faculty = facultyService.getFaculty(username);
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setName(faculty.getUser().getFullname());
        dashboardResponse.setRole(faculty.getUser().getRole().toString());
        dashboardResponse.setEmail(faculty.getUser().getEmail());
        dashboardResponse.setContact(faculty.getUser().getContact());
        dashboardResponse.setUsername(faculty.getId());
        dashboardResponse.setDepartment(faculty.getDepartment().getName());
        dashboardResponse.setOffice_hours(faculty.getOffice_hours());

        return new ResponseEntity<>(dashboardResponse , HttpStatus.OK);
    }
}
