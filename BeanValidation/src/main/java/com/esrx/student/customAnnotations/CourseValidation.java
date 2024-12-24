package com.esrx.student.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=CustomCourseValidation.class)
public @interface CourseValidation {
    String message() default "{course type is invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
