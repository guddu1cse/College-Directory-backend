package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class EnrollmentTrendChat {

    @Id
    private int id;
    private int[] trendList = new int[12];

}
