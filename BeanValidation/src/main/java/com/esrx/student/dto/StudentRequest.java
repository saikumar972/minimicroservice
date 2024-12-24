package com.esrx.student.dto;

import com.esrx.student.customAnnotations.CourseValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "student")
@NoArgsConstructor
public class StudentRequest {
    @NotBlank(message="name should be required")
    private String name;
    @Pattern(regexp="^\\d{10}$",message = "Phone number is not valid")
    private String phone;
    @Email(message="Invalid email")
    private String email;
    @Min(value=100, message="fee should be at least 100")
    @Max(value=10000, message="fee should be less than 10000")
    private int fee;
    @CourseValidation
    private String course;
}
