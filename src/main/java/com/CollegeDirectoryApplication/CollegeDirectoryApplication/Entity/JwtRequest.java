package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {

    private String userName;
    private String password;

}
