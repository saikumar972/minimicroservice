package com.acc.validate.customanootation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class CustomCourseValidation  implements ConstraintValidator<CourseValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> list= Arrays.asList("ECE","CSE");
        if(list.contains(value)){
            return true;
        }
        else{
            return false;
        }
    }
}
