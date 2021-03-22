package com.infosys.schoolservice.repositories;

import com.infosys.schoolservice.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
