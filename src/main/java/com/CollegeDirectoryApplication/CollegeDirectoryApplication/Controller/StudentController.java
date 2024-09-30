package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Controller;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Student;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


//@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Student>> getStudents(@PathVariable String keyword){
        return new ResponseEntity<>(studentService.searchByNameLike(keyword) , HttpStatus.OK);
    }

    @GetMapping("/currentuser")
    public ResponseEntity<Student> currentUser(){
        return new ResponseEntity<>(null , HttpStatus.OK);
    }

    @PutMapping("/update/{departmentId}")
    public ResponseEntity<String> update(Principal principal , @RequestBody Student newStudent , @PathVariable long departmentId){
        return new ResponseEntity<>(studentService.updateStudent(newStudent , principal.getName(), departmentId) , HttpStatus.OK);
    }

}
