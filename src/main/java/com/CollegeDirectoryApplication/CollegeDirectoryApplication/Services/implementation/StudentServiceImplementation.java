package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.*;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.*;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.EnrollmentTrendListService;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.*;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired private StudentRepo studentRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private EnrollmentRepo enrollmentRepo;
    @Autowired private DepartmentRepo departmentRepo;
    @Autowired private CourseRepo courseRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private EnrollmentTrendRepo enrollmentTrendRepo;
    @Autowired private EnrollmentTrendListService enrollmentTrendListService;


    @Override
    public String create(Student student , long departmentId) {
        try {
            Department department = departmentRepo.findById(departmentId).orElse(null);
            if(department == null) return "department not present in database for given id !";
            student.setDepartment(department);
            student.getUser().setRole(Role.STUDENT);
            student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));//password encode
            if(student.getSessionStart() == null){

                java.util.Date currDate = new java.util.Date();
                Date date = new Date(currDate.getYear()+1900,currDate.getMonth(),currDate.getDate());
                Date date1 = new Date(currDate.getYear()+1900+4,currDate.getMonth(),currDate.getDate());
                student.setSessionStart(date);
                student.setSessionEnd(date1);
            }
            studentRepo.save(student);
            enrollmentTrendListService.update();
            return "Student created !";
        } catch (Exception e){
            return "failed to create user";
        }
    }

    @Override
    public Student getById(String id) {
        return studentRepo.findById(id).orElse(null);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @Override
    public String delete(String id) {
        try {
            Student student = studentRepo.findById(id).orElse(null);
            if (student == null) return "student not present in database with given id ";
            User user = student.getUser();
            studentRepo.delete(student);
            userRepo.delete(user);
            return "user deleted !";
        } catch(Exception e){
            return "failed to Delete !\nReason: "+e.getMessage();
        }
    }

    @Override
    public List<Course> getCourse(String id) {
        List<Course> courses = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepo.findAll().stream().filter(
                enrollment -> (enrollment.getStudent().getId().equals(id))
        ).toList();
        for(Enrollment e: enrollments) courses.add(e.getCourse());
        return courses;
    }

    @Override
    public List<Student> searchByNameLike(String keyword) {
        List<User> users = userRepo.findByFullnameContainingIgnoreCase(keyword);
        List<Student> students = new ArrayList<>();
        for(User user : users){
            Student student = studentRepo.findById(user.getUsername()).orElse(null);
            if(user.getRole().toString().equals("STUDENT") && student!=null) students.add(student);
        }
        return students;
    }

    @Override
    public List<Faculty> getFaculty(String id) {
        List<Course> courses = getCourse(id);
        List<Faculty> faculties = new ArrayList<>();
        for(Course course : courses) faculties.add(course.getFaculty());
        return faculties;
    }

    @Override
    public List<Student> getStudentByFacultyId(String facultyId) {
        List<Course> courses = courseRepo.findAll().stream().filter(course -> Objects.equals(course.getFaculty().getId(), facultyId)).toList();
        Set<Long> courseId = new HashSet<Long>();
        courses.forEach(course -> courseId.add(course.getId()));
        Set<Student> students = new HashSet<>();
        enrollmentRepo.findAll().forEach(enrollment -> {
            if(courseId.contains(enrollment.getCourse().getId())) {
                Student student = studentRepo.findById(enrollment.getStudent().getId()).orElse(null);
                if(student!=null) students.add(student);
            }
        });
        return new ArrayList<>(students);
    }

    @Override
    public String updateStudent(Student newstudent , String studentId, long departmentId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        Department department = departmentRepo.findById(departmentId).orElse(null);
        if(student!=null && department!=null){
            student.setDepartment(department);
            student.getUser().setFullname(newstudent.getUser().getFullname());
            student.getUser().setContact(newstudent.getUser().getContact());
            student.getUser().setEmail(newstudent.getUser().getEmail());
            student.getUser().setPassword(passwordEncoder.encode(newstudent.getUser().getPassword()));
            student.setPhotourl(newstudent.getPhotourl());
            try{
                //encoding password
                student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));
                studentRepo.save(student);
                return "Student Details Details Updated !";
            } catch(Exception e){
                return "Failed update due to "+e.getMessage();
            }
        }

        return "something went wrong";
    }

    @Override
    public HashMap<String, Integer> getDepartmentAndStudents() {
        HashMap<String , Integer> departmentData = new HashMap<>();
        studentRepo.findAll().forEach(student -> {
            departmentData.put(student.getDepartment().getName(), departmentData.getOrDefault(student.getDepartment().getName(), 0)+1);
        });
        return departmentData;
    }


}
