package com.esrx.student.service;

import com.esrx.student.dto.StudentRequest;
import com.esrx.student.dto.StudentResponse;
import com.esrx.student.entity.StudentEntity;
import com.esrx.student.repo.StudentRepo;
import com.esrx.student.utils.Convertion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepo repo;

    @Nested
    class SaveStudentTestCases{
        @Test
        void saveStudentTest() {
            //data
            StudentRequest studentRequest = StudentRequest.student("sai", "8976786549", "sai@gmail.com", 5001, "ece");
            String mockRandomId = "mockRandomId";
            StudentEntity studentEntity = Convertion.covertRequestToEntity(studentRequest);

            when(repo.save(studentEntity)).thenReturn(studentEntity);

            StudentResponse actualResponse = studentService.saveStudent(studentRequest);

            StudentResponse expectedResponse = Convertion.convertEntityToResponse(studentEntity);
            // Manually set randomId for comparison
            actualResponse.setTransactionId(mockRandomId);
            expectedResponse.setTransactionId(mockRandomId);

            // Assertions
            assertEquals(expectedResponse, actualResponse);

            System.out.println(actualResponse);
            System.out.println(expectedResponse);
        }

        @Test
        void saveStudentExceptionTest() {
            //data
            StudentRequest studentRequest = StudentRequest.student("sai", "8976786549", "sai@gmail.com", 5000, "ece");

            // Assertions
            RuntimeException runtimeException=assertThrows(RuntimeException.class,()->{
                studentService.saveStudent(studentRequest);
            });
            assertEquals("fees is invalid",runtimeException.getMessage());
        }
    }
    @Nested
    class StudentListtestClass{
        @Test
        void studentListTest() {
            List<StudentEntity> studentEntityList= Stream.of(new StudentEntity(1,"sai","9876567545","sai@gmail.com",34000,"ece"),
                    new StudentEntity(2,"kumar","7689345678","kumar@gmail.com",34000,"cse"),
                    new StudentEntity(3,"sai","9876567545","sai@gmail.com",34000,"cse")).toList();
            when(repo.findAll()).thenReturn(studentEntityList);
            List<StudentResponse> expectedResponse=studentEntityList.stream().map(Convertion::convertEntityToResponse).peek(response->response.setTransactionId("dummy")).toList();
            //Assertions
            List<StudentResponse> actualResponse=studentService.studentList().stream().peek(response->response.setTransactionId("dummy")).toList();
            assertEquals(expectedResponse,actualResponse);
        }

        @Test
        void handlingZeroData() {
           when(repo.findAll()).thenReturn(Collections.emptyList());
            List<StudentResponse> actualResponse = studentService.studentList();
            assertTrue(actualResponse.isEmpty(), ()->"Expected an empty list when the repository has no students.");
        }

        @Test
        void handlingNullValues(){
            List<StudentEntity> studentEntityList= Stream.of(new StudentEntity(1,"sai","9876567545","sai@gmail.com",34000,"ece"),
                    new StudentEntity(2,"kumar","7689345678","kumar@gmail.com",34000,"cse"),
                    null).toList();
            when(repo.findAll()).thenReturn(studentEntityList);
            //Assertions
            assertThrows(NullPointerException.class,()->{
                studentService.studentList();
            });
        }

        @Test
        void shouldHandleDuplicateEntities() {
            // Arrange
            List<StudentEntity> studentEntityList = Stream.of(
                    new StudentEntity(1, "sai", "9876567545", "sai@gmail.com", 34000, "ece"),
                    new StudentEntity(1, "sai", "9876567545", "sai@gmail.com", 34000, "ece")
            ).toList();
            when(repo.findAll()).thenReturn(studentEntityList);

            // Act
            List<StudentResponse> actualResponse = studentService.studentList();

            // Assert
            assertEquals(2, actualResponse.size(), "Expected duplicate entries to be included in the response.");
        }

        @Test
        void shouldHandleRepositoryExceptions() {
            // Arrange
            when(repo.findAll()).thenThrow(new RuntimeException("Database connection error"));

            // Act & Assert
            assertThrows(RuntimeException.class, () -> studentService.studentList(),
                    "Expected RuntimeException when repository throws an error.");
        }
    }



    @Test
    void findStudentById() {
        StudentEntity studentEntity= new StudentEntity(1,"sai","9876567545","sai@gmail.com",34000,"ece");
        when(repo.findById(1)).thenReturn(Optional.of(studentEntity));
        StudentResponse expectedResponse=Convertion.convertEntityToResponse(studentEntity);
        expectedResponse.setTransactionId("dummy");
        StudentResponse actualResponse=studentService.findStudentById(1);
        actualResponse.setTransactionId("dummy");
        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void deleteStudentByIdTest() {
        doNothing().when(repo).deleteById(1);
        studentService.deleteStudentById(1);
        verify(repo,times(1)).deleteById(1);
    }

    @Test
    void privateMethodTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method=StudentService.class.getDeclaredMethod("validateStudentFees",double.class);
        method.setAccessible(true);
        boolean result= (boolean) method.invoke(studentService,5001);
        assertTrue(result);
    }
}