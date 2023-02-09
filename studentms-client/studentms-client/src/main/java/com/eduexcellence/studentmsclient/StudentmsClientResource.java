package com.eduexcellence.studentmsclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.eduexcellence.studentms.model.Student;

import java.util.Arrays;

@RestController
public class StudentmsClientResource {
    private static final Logger LOGGER= LoggerFactory.getLogger(StudentmsClientResource.class);
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/students-client/students")
    @HystrixCommand(fallbackMethod = "getStudentsFromFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
            })
    public Object getStudentsFromStudentms() {
        LOGGER.info("Calling Students Microservice - To Get All Students");
        return restTemplate.getForObject("http://studentms/students", Object.class);
    }

    @GetMapping("/students-client/students/{id}")
    @HystrixCommand(fallbackMethod = "getStudentsByIdFromFallback",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
            }
    )
    public Object getStudentsByIdFromStudentms(@PathVariable Integer id) {
        LOGGER.info("Calling Students Microservice - To Get Student by Id");
        return restTemplate.getForObject("http://studentms/students/{id}", Object.class,id);
    }

    @PostMapping("/students-client/students")
    @HystrixCommand(fallbackMethod = "createStudentsFromFallback",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
            }
    )
    public Object createStudentsinStudentms(@RequestBody Student student) {
        LOGGER.info("Calling Students Microservice - To Create a Student");
        return restTemplate.postForObject("http://studentms/students",student,Student.class);

    }

   @PutMapping("/students-client/students/{id}")
   @HystrixCommand(fallbackMethod = "updateStudentsFromFallback",
           commandProperties = {
                   @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
           }
   )
    public Object updateStudentinStudentms(@PathVariable Integer id,@RequestBody Student student) {
        LOGGER.info("Calling Students Microservice - To Update a Student");
        restTemplate.put("http://studentms/students",student,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/students-client/students/{id}")
    @HystrixCommand(fallbackMethod = "deleteStudentsFromFallback",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
            }
    )
    public Object deleteStudentsByIdFromStudentms(@PathVariable Integer id) {
        LOGGER.info("Calling Students Microservice - To Get Student by Id");
        restTemplate.delete("http://studentms/students/"+id,Object.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Fall Back Methods

    private Object getStudentsFromFallback() {
        LOGGER.error("Circuit Breaker Invoked");
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public Object getStudentsByIdFromFallback(Integer id) {
        LOGGER.error("Circuit Breaker Invoked");
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public Object createStudentsFromFallback(Student student) {
        LOGGER.error("Circuit Breaker Invoked");
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public Object updateStudentsFromFallback(Integer id,Student student) {
        LOGGER.error("Circuit Breaker Invoked");
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public Object deleteStudentsFromFallback(Integer id) {
        LOGGER.error("Circuit Breaker Invoked");
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
