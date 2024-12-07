package com.acc.validate.utils;

import com.acc.validate.dto.StudentRequest;
import com.acc.validate.dto.StudentResponse;
import com.acc.validate.entity.StudentEntity;

import java.util.UUID;

public class Convertion {
    public static StudentEntity covertRequestToEntity(StudentRequest request){
        StudentEntity entity = new StudentEntity();
        entity.setPhone(request.getPhone());
        entity.setFee(request.getFee());
        entity.setEmail(request.getEmail());
        entity.setName(request.getName());
        entity.setCourse(request.getCourse());
        return entity;
    }

    public static StudentResponse convertEntityToResponse(StudentEntity entity){
        StudentResponse response= new StudentResponse();
        response.setId(entity.getId());
        response.setCourse(entity.getCourse());
        response.setName(entity.getName());
        response.setFee(entity.getFee());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setRandomId(UUID.randomUUID().toString().split("-")[0]);
        return response;
    }
}
