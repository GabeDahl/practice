package com.infosys.studentservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data @NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String schoolid;
    private String email;
    private String phone;
    private Integer ssn;
    private String street;
    private String city;
    private String state;
    private Integer zipcode;

    @Transient
    private School school;

    public Student(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

}
