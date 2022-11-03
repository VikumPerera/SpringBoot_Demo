package com.springboot.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.domain.Department;
import com.springboot.demo.service.DepartmentService;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/departments")
	public Department saveDepartment(@RequestBody Department department) {
		return departmentService.saveDepartment(department);
	}
	
	@GetMapping("/departments/{id}")
	public Department getDepartmentById(@PathVariable Long id) {
		return departmentService.getDepartmentById(id);
	}
	
	@GetMapping("/departments")
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
	
	@PutMapping("/departments/{id}")
	public Department updateDepartmentById(@PathVariable Long id, @RequestBody Department department) { 
		return departmentService.updateDepartmentById(id, department);
	}
	
	@DeleteMapping("/departments/{id}")
	public void getAllDepartments(@PathVariable Long id) {
		departmentService.deleteDepartmentById(id);
	}
	
	@GetMapping("/departments/search/name")
	public Department getDepartmentById(@PathParam(value = "departmentName") String departmentName) {
		return departmentService.getDepartmentByName(departmentName);
	}
	
}
