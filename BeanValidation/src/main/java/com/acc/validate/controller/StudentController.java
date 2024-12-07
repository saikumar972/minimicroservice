package com.acc.validate.controller;

import com.acc.validate.EexceptionHandling.IdInValidException;
import com.acc.validate.annotations.CustomAnnotation;
import com.acc.validate.customResponseEntity.ServiceResponseEntity;
import com.acc.validate.dto.StudentRequest;
import com.acc.validate.dto.StudentResponse;
import com.acc.validate.entity.StudentEntity;
import com.acc.validate.service.StudentService;
import com.acc.validate.utils.StudentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService service;
    Logger log= LogManager.getLogger(StudentController.class);
    @PostMapping("/add")
    @CustomAnnotation
    public ServiceResponseEntity<StudentResponse> addData(@Valid @RequestBody  StudentRequest request){
        if(request.getFee()==1500){
            throw new IllegalArgumentException("1500 rupees not accepted pay any other amount");
        }
       StudentResponse response= service.saveStudent(request);
        log.info("StudentController :: adding data Successfully added to the db {}",StudentMapper.JasonMapper(response));
        ServiceResponseEntity<StudentResponse> serviceResponseEntity = new ServiceResponseEntity<>();
        serviceResponseEntity.setStatus(HttpStatus.CREATED);
        serviceResponseEntity.setResponse(response);
        return serviceResponseEntity;
    }
    @Operation(summary = "fetch the Student by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "student found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "invalid id")
    })
    @CustomAnnotation
    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponse> get(@PathVariable int id) throws IdInValidException {
        return ResponseEntity.ok(service.findStudentById(id));
    }
    @CustomAnnotation
    @GetMapping("/getstudent")
    public ServiceResponseEntity<StudentResponse> getStudentRequest(@RequestParam Integer studentId){
        ServiceResponseEntity<StudentResponse> serviceResponseEntity = new ServiceResponseEntity<>();
        serviceResponseEntity.setResponse(service.findStudentById(studentId));
        serviceResponseEntity.setStatus(HttpStatus.FOUND);
        return serviceResponseEntity;
    }
    @CustomAnnotation
    @GetMapping("/getAll")
    public ServiceResponseEntity<List<StudentResponse>> fetchAll(){
        ServiceResponseEntity<List<StudentResponse>> serviceResponseEntity= new ServiceResponseEntity<>();
        serviceResponseEntity.setStatus(HttpStatus.FOUND);
        serviceResponseEntity.setResponse(service.studentList());
        return serviceResponseEntity;

    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponseEntity<?> deleteStudentById(@PathVariable int id){
        ServiceResponseEntity<StudentResponse> serviceResponseEntity = new ServiceResponseEntity<>();
       service.deleteStudentById(id);
       log.debug("StudentController :: deleted the student record successfully by id {}",id);
        serviceResponseEntity.setStatus(HttpStatus.OK);
        return serviceResponseEntity;
    }
}
