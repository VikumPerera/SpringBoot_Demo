package com.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.demo.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM Department WHERE  department_name = :departmentName")
	Department getDepartmentByName(@Param("departmentName") String departmentName);

}
