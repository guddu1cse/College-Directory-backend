package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;
import ch.qos.logback.core.encoder.EchoEncoder;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Course;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Enrollment;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Student;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.CourseRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.EnrollmentRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.StudentRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImplementation implements EnrollmentService {
    @Autowired private CourseRepo courseRepo;
    @Autowired private StudentRepo studentRepo;
    @Autowired private EnrollmentRepo enrollmentRepo;


    @Override
    public String create(Long courseId, String studentId) {
        try {
            Student student = studentRepo.findById(studentId).orElse(null);
            if (student == null) return "student not present with given id !";
            Course course = courseRepo.findById(courseId).orElse(null);
            if (course == null) return "course not present with given id !";
            Enrollment enrollment = new Enrollment(null, student, course);

            Enrollment e = new Enrollment();
            e.setCourse(course);
            e.setStudent(student);
            enrollmentRepo.save(e);
            return "enrollment done !\nstudent id: " +student.getId() +"& course id: "+course.getId();
        } catch (Exception e){
            return "enrollment failed !\nReason: " + e.getMessage();
        }
    }

    @Override
    public List<Enrollment> getEnrollments() {
        return enrollmentRepo.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(long id) {
        return enrollmentRepo.findById(id).orElse(null);
    }

    @Override
    public String deleteEnrollment(long enrollmentId) {
        try {
            Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElse(null);
            if(enrollment==null) return "enrollment not present in database with given id";
            enrollmentRepo.delete(enrollment);
            return "enrollment deleted !";
        } catch (Exception e){
            return "failed to delete !\nReason: "+e.getMessage();
        }
    }

    @Override
    public Enrollment getEnrollment(long id) {
        return enrollmentRepo.findById(id).orElse(null);
    }
}
