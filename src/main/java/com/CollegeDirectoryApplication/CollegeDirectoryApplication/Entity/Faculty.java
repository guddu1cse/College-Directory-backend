package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Faculty {

    @Id
    private String id;


    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;

    @ManyToOne()
    private Department department;

    private String photourl;
    private String[] office_hours = new String[2]; //{From , To} //hh:mm

}
