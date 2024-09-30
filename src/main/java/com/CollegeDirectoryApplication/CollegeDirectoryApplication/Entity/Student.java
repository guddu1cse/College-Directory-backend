package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Student {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user; //every student , faculty , admin is user // login performed on user data table

    private String photourl;
    private Date sessionStart;
    private Date sessionEnd;

    @ManyToOne
    private Department department;

    private String year;
}
