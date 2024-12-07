package com.acc.validate.customResponseEntity;

import com.acc.validate.dto.ErrorDTo;
import com.acc.validate.dto.StudentResponse;
import com.acc.validate.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseEntity<T> {
    private HttpStatus status;
    private T response;
    private List<ErrorDTo> errorList;

}
