package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Search {
    private String username;
    private String department;
    private String photourl;
    private String contact;
    private String role;
    private String email;
    private String name;

    public void setDepartment(String name){
        this.department = name;
    }
}
