package com.springboot.demo.service;

import java.util.List;

import com.springboot.demo.domain.Department;
import com.springboot.demo.dto.DepartmentDTO;


public interface DepartmentService {

	DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

	Department getDepartmentById(Long id);

	List<Department> getAllDepartments();

	Department updateDepartmentById(Long id, Department department);

	void deleteDepartmentById(Long id);

	Department getDepartmentByName(String departmentName);


}
