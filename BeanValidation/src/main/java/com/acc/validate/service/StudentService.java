package com.acc.validate.service;

import com.acc.validate.EexceptionHandling.IdInValidException;
import com.acc.validate.EexceptionHandling.StudentAddException;
import com.acc.validate.annotations.CustomAnnotation;
import com.acc.validate.dto.StudentRequest;
import com.acc.validate.dto.StudentResponse;
import com.acc.validate.entity.StudentEntity;
import com.acc.validate.repo.StudentRepo;
import com.acc.validate.utils.Convertion;
import com.acc.validate.utils.StudentMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepo repo;
     Logger log= LogManager.getLogger(StudentService.class);
    @CustomAnnotation
    public StudentResponse saveStudent(StudentRequest request){
        StudentEntity entity=null;
        try{
             entity=Convertion.covertRequestToEntity(request);
            repo.save(entity);
            StudentResponse response=Convertion.convertEntityToResponse(entity);
            response.setRandomId(UUID.randomUUID().toString().split("-")[0]);
            log.debug("StudentService :: Data successfully added to db {}", StudentMapper.JasonMapper(response));
            return response;
        }catch (Exception e){
            log.error("StudentService :: Unable to add in db because of bad inputs {}",e.getMessage());
            throw new StudentAddException("Unable to add Student");
        }

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
        StudentEntity entity = repo.findById(id).orElseThrow(()->new IdInValidException("Id is invalid "+id));
        log.debug("StudentService :: deleted the student data by id {}",id);
        repo.delete(entity);
    }
}
