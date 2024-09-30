package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.EnrollmentTrendChat;

import java.util.Date;
import java.util.List;

public interface EnrollmentTrendListService {

    int[] getLast1YearList();

    void update();
    EnrollmentTrendChat getThisYearEnrollment();

}
