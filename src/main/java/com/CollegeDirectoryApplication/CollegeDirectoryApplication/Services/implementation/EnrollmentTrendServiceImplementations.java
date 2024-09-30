package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.implementation;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.EnrollmentTrendChat;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Repo.EnrollmentTrendRepo;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Services.EnrollmentTrendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class EnrollmentTrendServiceImplementations implements EnrollmentTrendListService {

    @Autowired
    private EnrollmentTrendRepo enrollmentTrendRepo;

    @Override
    public int[] getLast1YearList() {
        int[] list = new int[12];
        Date currDate = new Date();
        int[] currYear = enrollmentTrendRepo.findById(currDate.getYear()+1900).orElse(
                new EnrollmentTrendChat(currDate.getYear()+1900 , new int[12])
        ).getTrendList();

        int[] previousYear = enrollmentTrendRepo.findById(currDate.getYear()+1900-1).orElse(
                new EnrollmentTrendChat(currDate.getYear()+1900-1 , new int[12])
        ).getTrendList();

        int currMonth = currDate.getMonth();

        int idx = 0;
        for(int y=currMonth+1 ; y<12 ; y++) list[idx++] = previousYear[y];
        for(int y=0; idx<12 ; y++) list[idx++] = currYear[y];

        return list;
    }

    @Override
    public void update() {
        Date currDate = new Date();
        EnrollmentTrendChat enrollmentTrendChat = enrollmentTrendRepo.findById(currDate.getYear()+1900).orElse(new EnrollmentTrendChat(currDate.getYear()+1900 , new int[12]));
        enrollmentTrendChat.getTrendList()[currDate.getMonth()]++;
        enrollmentTrendRepo.save(enrollmentTrendChat);
    }

    @Override
    public EnrollmentTrendChat getThisYearEnrollment() {
        Date date = new Date();
        return enrollmentTrendRepo.findById(date.getYear()+1900).orElse(new EnrollmentTrendChat(date.getYear()+1900 , new int[12]));
    }
}
