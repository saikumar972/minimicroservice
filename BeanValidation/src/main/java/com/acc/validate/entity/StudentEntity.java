package com.acc.validate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NoArgsConstructor//(staticName = "student")
@Entity
public class StudentEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phone;
    private String email;
    private int fee;
    private String course;
}
