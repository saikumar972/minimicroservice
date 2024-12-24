package com.esrx.student.service;

import com.esrx.student.ExceptionHandling.IdInValidException;
import com.esrx.student.ExceptionHandling.StudentAddException;
import com.esrx.student.annotations.CustomAnnotation;
import com.esrx.student.dto.StudentRequest;
import com.esrx.student.dto.StudentResponse;
import com.esrx.student.entity.StudentEntity;
import com.esrx.student.repo.StudentRepo;
import com.esrx.student.utils.Convertion;
import com.esrx.student.utils.StudentMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepo repo;

     Logger log= LogManager.getLogger(StudentService.class);
    @CustomAnnotation
    public StudentResponse saveStudent(StudentRequest request) {
        StudentEntity entity = null;
        if (validateStudentFees(request.getFee())) {
            entity = Convertion.covertRequestToEntity(request);
            repo.save(entity);
        } else {
            throw new RuntimeException("fees is invalid");
        }
        StudentResponse response = Convertion.convertEntityToResponse(entity);
        response.setTransactionId(UUID.randomUUID().toString().split("-")[0]);
        log.debug("StudentService :: Data successfully added to db {}", StudentMapper.JasonMapper(response));
        return response;
    }

    @CustomAnnotation
    public List<StudentResponse> studentList(){
        List<StudentResponse> responses= new ArrayList<>();
        List<StudentEntity> studentEntities=repo.findAll();
        responses=studentEntities.stream().map(Convertion::convertEntityToResponse).toList();
        return responses;
    }

    @CustomAnnotation
    public StudentResponse findStudentById(Integer id) {
        StudentEntity entity = repo.findById(id).orElseThrow(()->new IdInValidException("Id is invalid "+id));
        StudentResponse response=Convertion.convertEntityToResponse(entity);
        log.debug("StudentService :: retrieved the student data by id {}",id);
        return response;
    }

    public void deleteStudentById(int id){
        log.debug("StudentService :: deleted the student data by id {}",id);
        repo.deleteById(id);
    }

    private boolean validateStudentFees(double fees){
        return fees != 5000;
    }
}
