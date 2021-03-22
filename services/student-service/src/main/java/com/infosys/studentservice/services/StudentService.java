package com.infosys.studentservice.services;

import com.infosys.studentservice.clients.SchoolClient;
import com.infosys.studentservice.models.CustomException;
import com.infosys.studentservice.models.School;
import com.infosys.studentservice.models.Student;
import com.infosys.studentservice.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SchoolClient schoolClient;

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student getStudent(Integer id) throws CustomException {
        Optional<Student> student = this.studentRepository.findById(id);
        Student s;
        if (student.isPresent()) {
            s = student.get();
            System.out.println("is present");
        } else {
            throw new CustomException("No student with given ID", HttpStatus.NOT_FOUND);
        }
        try {
            School school = schoolClient.getSchool(s.getSchoolid());
            s.setSchool(school);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public Student addStudent(Student student) throws CustomException {
        try {
            studentRepository.saveAndFlush(student);
            return studentRepository.findById(student.getId()).get();
        } catch (Exception e) {
            throw new CustomException("Something went wrong. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Student updateStudent(Student student) throws CustomException {
        Optional<Student> student1 = studentRepository.findById(student.getId());
        if (student1.isPresent()) {
            Student s = student1.get();
            s.setName(student.getName());
            s.setSchool(student.getSchool());
            s.setCity(student.getCity());
            s.setEmail(student.getEmail());
            s.setPhone(student.getPhone());
            s.setSchoolid(student.getSchoolid());
            s.setSsn(student.getSsn());
            s.setState(student.getState());
            s.setStreet(student.getStreet());
            s.setZipcode(student.getZipcode());
            studentRepository.saveAndFlush(s);
            return s;
        } else {
            throw new CustomException("No student with given ID", HttpStatus.NOT_FOUND);
        }
    }

    public String deleteStudent(Student student) throws CustomException {
        try {
            this.studentRepository.delete(student);
            this.studentRepository.flush();
            return "Student deleted successfully";
        } catch (Exception e) {
            throw new CustomException("Something went wrong. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
