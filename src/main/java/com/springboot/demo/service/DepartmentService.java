package com.springboot.demo.service;

import java.util.List;

import com.springboot.demo.domain.Department;


public interface DepartmentService {

	Department saveDepartment(Department department);

	Department getDepartmentById(Long id);

	List<Department> getAllDepartments();

	Department updateDepartmentById(Long id, Department department);

	void deleteDepartmentById(Long id);

	Department getDepartmentByName(String departmentName);


}
