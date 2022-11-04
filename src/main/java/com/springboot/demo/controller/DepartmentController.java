package com.springboot.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.dto.DepartmentDTO;
import com.springboot.demo.dto.response.ResponseDTO;
import com.springboot.demo.enums.ResultStatus;
import com.springboot.demo.exception.TransformerException;
import com.springboot.demo.service.DepartmentService;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/departments")
	public ResponseDTO<?> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) throws TransformerException {
		
		ResponseDTO<DepartmentDTO> response = new ResponseDTO<>();
		response.setPayload(departmentService.saveDepartment(departmentDTO));
        return updateResponse(response);
	}

	@GetMapping("/departments/{id}")
	public ResponseDTO<?> getDepartmentById(@PathVariable Long id) throws EntityNotFoundException, TransformerException {

		ResponseDTO<DepartmentDTO> response = new ResponseDTO<>();
		response.setPayload(departmentService.getDepartmentById(id));
		return updateResponse(response);
		
	}
	
	@GetMapping("/departments")
	public List<DepartmentDTO> getAllDepartments() throws TransformerException {
		return departmentService.getAllDepartments();
	}
	
	@PutMapping("/departments/{id}")
	public DepartmentDTO updateDepartmentById(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) throws TransformerException { 
		return departmentService.updateDepartmentById(id, departmentDTO);
	}
	
	@DeleteMapping("/departments/{id}")
	public void getAllDepartments(@PathVariable Long id) throws TransformerException {
		departmentService.deleteDepartmentById(id);
	}
	
	@GetMapping("/departments/search/name")
	public DepartmentDTO getDepartmentById(@PathParam(value = "departmentName") String departmentName) throws TransformerException {
		return departmentService.getDepartmentByName(departmentName);
	}
	
	
	private ResponseDTO<?> updateResponse(ResponseDTO<?> response) {
		response.setResultStatus(ResultStatus.SUCCESSFUL);
        response.setHttpStatus(HttpStatus.OK);
        response.setHttpCode(response.getHttpStatus().toString());
		return response;
	}
	
}
