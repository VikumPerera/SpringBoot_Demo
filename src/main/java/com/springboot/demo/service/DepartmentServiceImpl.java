package com.springboot.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.domain.Department;
import com.springboot.demo.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.saveAndFlush(department);
	}

	@Override
	public Department getDepartmentById(Long id) {
		return departmentRepository.findById(id).get();
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department updateDepartmentById(Long id, Department department) {
		Department savedDepartment = departmentRepository.findById(id).orElse(null);
		
		if (null != savedDepartment) {
			if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
				savedDepartment.setDepartmentName(department.getDepartmentName());
			}
			if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
				savedDepartment.setDepartmentAddress(department.getDepartmentAddress());
			}
			if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
				savedDepartment.setDepartmentCode(department.getDepartmentCode());
			}
			
			return departmentRepository.saveAndFlush(savedDepartment);
			
		}
		
		return departmentRepository.saveAndFlush(department);
	}

	@Override
	public void deleteDepartmentById(Long id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public Department getDepartmentByName(String departmentName) {
		return departmentRepository.getDepartmentByName(departmentName);
	}

	

	
	

	

}
