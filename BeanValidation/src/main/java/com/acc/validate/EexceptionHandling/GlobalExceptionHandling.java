package com.acc.validate.EexceptionHandling;

import com.acc.validate.customResponseEntity.ServiceResponseEntity;
import com.acc.validate.dto.ErrorDTo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandling {
    /* @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(MethodArgumentNotValidException.class)
     public Map<String, String> handling(MethodArgumentNotValidException ex){
         Map<String, String> map=new HashMap<>();
         ex.getBindingResult().getFieldErrors().
         forEach((e)->map.put(e.getField(),e.getDefaultMessage()));
         return map;
     }*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceResponseEntity<?> hadlingincoming(MethodArgumentNotValidException exception) {
        ServiceResponseEntity<?> response=new ServiceResponseEntity<>();
        List<ErrorDTo> list = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach((exceptions) ->
                        list.add(new ErrorDTo(exceptions.getDefaultMessage()))
                );
        response.setErrorList(list);
        response.setStatus(HttpStatus.BAD_REQUEST);
        return response;
    }

     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     @ExceptionHandler(IdInValidException.class)
     public Map<String, String> handling2(IdInValidException ex){
         Map<String, String> errorMap=new HashMap<>();
         errorMap.put("Error Message: ",ex.getMessage());
         return errorMap;
     }

    //urike
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StudentAddException.class)
    public Map<String, String> handling3(StudentAddException ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("Error Message: ",ex.getMessage());
        return errorMap;
    }
}