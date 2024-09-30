package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DashboardResponse {

    private String name;
    private String username;
    private String department;
    private String photourl;
    private String sessionStart;
    private String sessionEnd;
    private String Department;
    private String year;
    private String role;
    private String email;
    private String contact;
    private String[] office_hours = new String[2];

    public void setDepartment(String dept) {
        this.department = dept;
    }
}

//all the user can access the accesible data and other field ke null
