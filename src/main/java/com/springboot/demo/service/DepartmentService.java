package com.springboot.demo.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.springboot.demo.dto.DepartmentDTO;
import com.springboot.demo.exception.TransformerException;


public interface DepartmentService {

	DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) throws TransformerException;

	DepartmentDTO getDepartmentById(Long id) throws EntityNotFoundException, TransformerException;

	List<DepartmentDTO> getAllDepartments() throws TransformerException;

	DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departdepartmentDTOment) throws TransformerException;

	void deleteDepartmentById(Long id) throws TransformerException;

	DepartmentDTO getDepartmentByName(String departmentName) throws TransformerException;


}
