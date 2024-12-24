package com.esrx.student.utils;

import com.esrx.student.dto.StudentRequest;
import com.esrx.student.dto.StudentResponse;
import com.esrx.student.entity.StudentEntity;

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
        response.setTransactionId(UUID.randomUUID().toString().split("-")[0]);
        return response;
    }
}
