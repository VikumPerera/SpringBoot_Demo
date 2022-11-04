package com.springboot.demo.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.constant.EntityNotFoundConstatnt;
import com.springboot.demo.domain.Department;
import com.springboot.demo.dto.DepartmentDTO;
import com.springboot.demo.exception.TransformerException;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.transformer.DepartmentTransformer;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DepartmentTransformer departmentTransformer;
	
	private final Logger LOGGER =LoggerFactory.getLogger(DepartmentService.class);

	@Override
	public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) throws TransformerException {
		Department department = departmentTransformer.transformDTOToDomain(departmentDTO);
		department.getStudents().stream().forEach(student -> student.setDepartment(department));
		return departmentTransformer.transformDomainToDTO(departmentRepository.saveAndFlush(department));
	}

	@Override
	public DepartmentDTO getDepartmentById(Long id) throws EntityNotFoundException, TransformerException {
		if (!departmentRepository.existsById(id)) {
			throw new EntityNotFoundException(EntityNotFoundConstatnt.DEPARTMENT_NOT_FOUND + id);
		}
		return departmentTransformer.transformDomainToDTO(departmentRepository.findById(id).get());
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() throws TransformerException {
		return departmentTransformer.transformDomainToDTO(departmentRepository.findAll());
	}

	@Override
	public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departmentDTO) throws TransformerException {
		Department savedDepartment = departmentRepository.findById(id).orElse(null);
		
		if (null != savedDepartment) {
			if (Objects.nonNull(departmentDTO.getDepartmentName()) && !"".equalsIgnoreCase(departmentDTO.getDepartmentName())) {
				savedDepartment.setDepartmentName(departmentDTO.getDepartmentName());
			}
			if (Objects.nonNull(departmentDTO.getDepartmentAddress()) && !"".equalsIgnoreCase(departmentDTO.getDepartmentAddress())) {
				savedDepartment.setDepartmentAddress(departmentDTO.getDepartmentAddress());
			}
			if (Objects.nonNull(departmentDTO.getDepartmentCode()) && !"".equalsIgnoreCase(departmentDTO.getDepartmentCode())) {
				savedDepartment.setDepartmentCode(departmentDTO.getDepartmentCode());
			}
			
			return departmentTransformer.transformDomainToDTO(departmentRepository.saveAndFlush(savedDepartment));
			
		}
		
		return null;
	}

	@Override
	public void deleteDepartmentById(Long id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public DepartmentDTO getDepartmentByName(String departmentName) throws TransformerException {
		return departmentTransformer.transformDomainToDTO(departmentRepository.getDepartmentByName(departmentName));
	}

	

	
	

	

}
