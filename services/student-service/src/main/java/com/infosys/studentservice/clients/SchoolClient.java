package com.infosys.studentservice.clients;

import com.infosys.studentservice.models.School;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SchoolMS")
public interface SchoolClient {
    @GetMapping("/{id}")
    public School getSchool(@PathVariable(name = "id") String id);
}
