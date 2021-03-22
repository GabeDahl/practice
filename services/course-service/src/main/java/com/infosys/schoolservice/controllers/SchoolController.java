package com.infosys.schoolservice.controllers;

import com.infosys.schoolservice.models.School;
import com.infosys.schoolservice.models.CustomException;
import com.infosys.schoolservice.services.SchoolService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/all")
    public List<School> getAllSchools() {
        return this.schoolService.getAllCourses();
    }

    @GetMapping("/fake")
    public ResponseEntity<School> getFake() throws CustomException {
        School s =  new School();
        s.setName("Math");
        s.setId(1234);
        logger.info("Hello Sleuth");
        return new ResponseEntity(s, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchool(@PathVariable Integer id) throws CustomException {
        return new ResponseEntity<>(this.schoolService.getCourse(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<School> addSchool(@RequestBody School school) throws CustomException {
        return new ResponseEntity<School>(this.schoolService.addCourse(school), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<School> updateSchool(@RequestBody School school) throws CustomException {
        return new ResponseEntity<>(this.schoolService.updateCourse(school), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSchool(@RequestBody School school) throws CustomException {
        return new ResponseEntity<>(this.schoolService.deleteCourse(school), HttpStatus.OK);
    }


    public List<School> fallback() {
        System.out.println("in fallback");
        return null;
    }

}
