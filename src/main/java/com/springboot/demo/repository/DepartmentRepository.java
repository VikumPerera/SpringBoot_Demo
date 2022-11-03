package com.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.demo.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
