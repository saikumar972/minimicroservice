package com.acc.faculty.service;

import com.acc.faculty.dto.ServiceResponseEntity;
import com.acc.faculty.dto.StudentRequest;
import com.acc.faculty.dto.StudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FacultyService {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/student/";

    public ServiceResponseEntity<StudentResponse> postData(StudentRequest request) {

        return restTemplate.postForObject(BASE_URL + "add", request, ServiceResponseEntity.class);
    }

    public ServiceResponseEntity<StudentResponse> getStudentById(int id) {
        log.info("FacultyService :: calling resttemplate to fetch data");
        System.out.println(restTemplate.getForObject(BASE_URL + "get/" + id, String.class, id));
        return restTemplate.getForObject(BASE_URL + "get/" + id, ServiceResponseEntity.class, id);
    }

    public ServiceResponseEntity<StudentResponse> getStudentByIdRequestParam(Integer studentId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("studentId", studentId);
        return restTemplate.getForObject(BASE_URL + "getstudent", ServiceResponseEntity.class, map);
    }

    public ServiceResponseEntity<List<StudentResponse>> getAllStudents() {
        return restTemplate.getForObject(BASE_URL + "getAll", ServiceResponseEntity.class);
    }
}
