package com.acc.faculty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "student")
@NoArgsConstructor
public class StudentRequest {
    private String name;
    private String phone;
    private String email;
    private int fee;
    private String course;
}
