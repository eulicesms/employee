package com.babel.employee.repository;

import com.babel.employee.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
}
