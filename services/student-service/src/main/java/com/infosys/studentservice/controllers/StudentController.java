package com.infosys.studentservice.controllers;

import com.infosys.studentservice.models.CustomException;
import com.infosys.studentservice.models.Student;
import com.infosys.studentservice.repositories.StudentRepository;
import com.infosys.studentservice.services.StudentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    DiscoveryClient discoveryClient;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return this.studentService.getAllStudents();
    }

    @GetMapping("/fake")
    public ResponseEntity<Student> getFake() throws CustomException {
        Student s =  new Student();
        s.setName("Gabe");
        s.setId(1234);
        logger.info("Hello Sleuth");
        URI uri = discoveryClient.getInstances("SchoolMS").get(0).getUri();

        return new ResponseEntity(s, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) throws CustomException {
        return new ResponseEntity<>(this.studentService.getStudent(id), HttpStatus.OK);
    }

    @GetMapping("/login")
    public void loginStuff() {
        logger.info("we here");
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws CustomException {
        return new ResponseEntity<Student>(this.studentService.addStudent(student), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) throws CustomException {
        return new ResponseEntity<>(this.studentService.updateStudent(student), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestBody Student student) throws CustomException {
        return new ResponseEntity<>(this.studentService.deleteStudent(student), HttpStatus.OK);
    }


    public List<Student> fallback() {
        System.out.println("in fallback");
        return null;
    }

}
