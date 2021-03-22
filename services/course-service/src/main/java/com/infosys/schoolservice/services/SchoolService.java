package com.infosys.schoolservice.services;

import com.infosys.schoolservice.models.School;
import com.infosys.schoolservice.models.CustomException;
import com.infosys.schoolservice.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    public List<School> getAllCourses() {
        return this.schoolRepository.findAll();
    }

    public School getCourse(Integer id) throws CustomException {
        Optional<School> course = this.schoolRepository.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new CustomException("No course with given ID", HttpStatus.NOT_FOUND);
        }
    }

    public School addCourse(School school) throws CustomException {
        if (schoolRepository.findById(school.getId()).isPresent()) {
            throw new CustomException("Course with given ID already exists", HttpStatus.CONFLICT);
        } else {
            try {
                schoolRepository.saveAndFlush(school);
                return schoolRepository.findById(school.getId()).get();
            } catch (Exception e) {
                throw new CustomException("Something went wrong. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public School updateCourse(School school) throws CustomException {
        Optional<School> course1 = schoolRepository.findById(school.getId());
        if (course1.isPresent()) {
            School c = course1.get();
            c.setName(school.getName());
            schoolRepository.saveAndFlush(c);
            return c;
        } else {
            throw new CustomException("No course with given ID", HttpStatus.NOT_FOUND);
        }
    }

    public String deleteCourse(School school) throws CustomException {
        try {
            this.schoolRepository.delete(school);
            this.schoolRepository.flush();
            return "Course deleted successfully";
        } catch (Exception e) {
            throw new CustomException("Something went wrong. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
