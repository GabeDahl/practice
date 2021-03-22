package com.infosys.schoolservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "school")
@Data @NoArgsConstructor
public class School {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public School(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

}
