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
public class Administrator {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;

    @ManyToOne()
    private Department department;

    private String photourl;
}
