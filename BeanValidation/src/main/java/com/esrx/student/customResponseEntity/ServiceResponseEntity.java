package com.esrx.student.customResponseEntity;

import com.esrx.student.dto.ErrorDTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
