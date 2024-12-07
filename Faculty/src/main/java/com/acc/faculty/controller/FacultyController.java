package com.acc.faculty.controller;

import com.acc.faculty.dto.ServiceResponseEntity;
import com.acc.faculty.dto.StudentRequest;
import com.acc.faculty.dto.StudentResponse;
import com.acc.faculty.service.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@Slf4j
public class FacultyController {
    @Autowired
    FacultyService service;
    @PostMapping("/add")
    public ServiceResponseEntity<StudentResponse> postStudentData(@RequestBody StudentRequest request){
        return service.postData(request);
    }
    @GetMapping("/get/{id}")
    public ServiceResponseEntity<StudentResponse> getStudentdataById(@PathVariable int id){
        return service.getStudentById(id);
    }
    @GetMapping("/get")
    public ServiceResponseEntity<StudentResponse> getStudentrequestParam(@RequestParam  Integer studentId){
        return service.getStudentByIdRequestParam(studentId);
    }

    @GetMapping("/getAll")
    public ServiceResponseEntity<List<StudentResponse>> getAllStudentData(){
        return service.getAllStudents();
    }
}
