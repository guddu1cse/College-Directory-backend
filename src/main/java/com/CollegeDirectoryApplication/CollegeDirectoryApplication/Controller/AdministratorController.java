package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Controller;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.*;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    //managing all the service
    @Autowired private UserService userService;
    @Autowired private StudentService studentService;
    @Autowired private CourseService courseService;
    @Autowired private FacultyService facultyService;
    @Autowired private EnrollmentService enrollmentService;
    @Autowired private DepartmentService departmentService;
    @Autowired private AdministratorService administratorService;
    @Autowired private EnrollmentTrendListService enrollmentTrendListService;


    //creating student
    @PostMapping("/student/create/{departmentId}")
    public ResponseEntity<String> createStudent(@RequestBody Student student , @PathVariable long departmentId){
        return new ResponseEntity<>(studentService.create(student , departmentId) , HttpStatus.OK);
    }

    //updating student
    @PutMapping("/student/update/{studentId}/{departmentId}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student , @PathVariable String studentId , @PathVariable long departmentId){
        return new ResponseEntity<>(studentService.updateStudent(student , studentId , departmentId) , HttpStatus.OK);
    }

    //Deleting student by studentId
    @DeleteMapping("/student/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId){
        return new ResponseEntity<>(studentService.delete(studentId) , HttpStatus.OK);
    }

    //Get Student
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudent(){
        return new ResponseEntity<>(studentService.getStudents() , HttpStatus.OK);
    }

    //Get Student by id
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId){
        return new ResponseEntity<>(studentService.getById(studentId) ,HttpStatus.OK);
    }

    //creating faculty
    @PostMapping("/faculty/create/{departmentId}")
    public ResponseEntity<String> createFaculty(@RequestBody Faculty faculty , @PathVariable long departmentId){
        return new ResponseEntity<>(facultyService.create(faculty , departmentId) , HttpStatus.OK);
    }

    //updating faculty
    @PutMapping("/faculty/update/{facultyId}/{departmentId}")
    public ResponseEntity<String> updateFaculty(@RequestBody Faculty faculty ,@PathVariable String facultyId, @PathVariable long departmentId){
        return new ResponseEntity<>(facultyService.update(facultyId ,departmentId ,faculty) , HttpStatus.OK);
    }

    //deleting faculty
    @DeleteMapping("/faculty/delete/{facultyId}")
    public ResponseEntity<String> deleteFaculty(@PathVariable String facultyId){
        return new ResponseEntity<>( facultyService.delete(facultyId) , HttpStatus.OK);
    }

    //create admin
    @PostMapping("/admin/create/{departmentId}")
    public ResponseEntity<String> createAdmin(@RequestBody Administrator administrator, @PathVariable long departmentId){
        return new ResponseEntity<>(administratorService.create(administrator , departmentId) , HttpStatus.OK);
    }

    //update admin
    @PutMapping("/admin/update/{adminId}/{departmentId}")
    public ResponseEntity<String> updateAdmin(@RequestBody Administrator administrator , @PathVariable String adminId , @PathVariable long departmentId){
        return new ResponseEntity<>(administratorService.update(adminId , administrator , departmentId ) , HttpStatus.OK);
    }

    //delete admin
    @DeleteMapping("/admin/delete/{adminId}")
    public ResponseEntity<String> deleteAdmin(String adminId){
        return new ResponseEntity<>(administratorService.delete(adminId) , HttpStatus.OK);
    }

    //Get Admin by id
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<Administrator> getAdminByID( @PathVariable  String adminId){
        return new ResponseEntity<>(administratorService.getAdinById(adminId) , HttpStatus.OK);
    }

    //get all admin
    @GetMapping("/admin")
    public ResponseEntity<List<Administrator>> getAdmin(){
        return new ResponseEntity<>(administratorService.getAll() , HttpStatus.OK);
    }


    //create Department
    @PostMapping("/department/create/")
    public ResponseEntity<String> createDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.create(department) , HttpStatus.OK);
    }

    //update department
    @PutMapping("/department/update/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable long id , @RequestBody Department department){
        return new ResponseEntity<>(departmentService.update(id , department) , HttpStatus.OK);
    }

    //getDepartments
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartment(){
        return new ResponseEntity<>(departmentService.getDepartments() , HttpStatus.OK);
    }

    //delete departments
    @DeleteMapping("/department/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable long id){
        return new ResponseEntity<>(departmentService.delete(id) , HttpStatus.OK);
    }

    //search department by id
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable long id){
        return new ResponseEntity<>( departmentService.getDepartment(id) , HttpStatus.OK);
    }

    //create enrollment
    @PostMapping("/enrollment/create/{studentId}/{courseId}")
    public ResponseEntity<String> createEnrollment(@PathVariable String studentId , @PathVariable long courseId){
        return new ResponseEntity<>(enrollmentService.create(courseId , studentId) , HttpStatus.OK);
    }


    //getFaculty
    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getFaculty(){
        return new ResponseEntity<>(facultyService.getFaculties() , HttpStatus.OK);
    }

    //create course
    @PostMapping("/course/create/{facultyId}/{departmentId}")
    public ResponseEntity<String> createCourse(@RequestBody Course course , @PathVariable String facultyId , @PathVariable long departmentId){
        return new ResponseEntity<>(courseService.createCourse(course , facultyId , departmentId) , HttpStatus.OK);
    }

    //get course by student id
    @GetMapping("/getCourses/{studentId}")
    public ResponseEntity<List<Course>> getCourses(@PathVariable String studentId){
        return new ResponseEntity<>(studentService.getCourse(studentId), HttpStatus.OK);
    }

    @GetMapping("/studentByFacultyId/{facultyId}")
    public ResponseEntity<List<Student>> getStudentByFaculty(@PathVariable String facultyId){
        return new ResponseEntity<>(studentService.getStudentByFacultyId(facultyId) , HttpStatus.OK);
    }

    @GetMapping("/departmentdata")
    public ResponseEntity<List<departmentData>> getDepartmentChart(){
        HashMap<String , Integer> data = studentService.getDepartmentAndStudents();
        List<departmentData> list = new ArrayList<>();
        data.entrySet().forEach(entry->{
            list.add(new departmentData(entry.getKey() , entry.getValue()));
        });
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class departmentData{
        private String name;
        private int students;
    }

    @GetMapping("/enrollmenttrend")
    public ResponseEntity<List<Map>> getLastYear(){
        Map map = new Map();
        int arr[] = enrollmentTrendListService.getLast1YearList();
        String months[] = {
                "January",
                "February",
                "March",
                 "April",
                 "May",
                 "June",
                 "July",
                 "August",
                 "September",
                 "October",
                "November",
                "December"
        };

        List<Map> list = new ArrayList<>();
        Date currDate = new Date();
        int idx =currDate.getMonth();

        for(int y=idx,count=0; count<12 ;count++,y--){
            int m = y<0 ? 12+y: y;
            list.add(new Map(months[m] , arr[12-count-1]));
        }
        return new ResponseEntity<>(list.reversed() , HttpStatus.OK);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Map{
        @Getter
        private String month;
        private int enrollments;
    }

}

