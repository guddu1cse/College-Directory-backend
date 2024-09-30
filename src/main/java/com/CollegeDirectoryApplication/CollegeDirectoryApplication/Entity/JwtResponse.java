package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;
    private String username;
    private String message;

}