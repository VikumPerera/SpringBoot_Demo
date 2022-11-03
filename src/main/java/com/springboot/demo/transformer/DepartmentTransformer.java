package com.springboot.demo.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.demo.domain.Department;
import com.springboot.demo.dto.DepartmentDTO;
import com.springboot.demo.exception.TransformerException;

@Component
public class DepartmentTransformer extends AbstractTransformer<Department, DepartmentDTO> {
	
	@Autowired
	StudentTransformer studentTransformer;

	@Override
	public DepartmentDTO transformDomainToDTO(Department domain) throws TransformerException {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(domain.getDepartmentId());
		dto.setDepartmentName(domain.getDepartmentName());
		dto.setDepartmentAddress(domain.getDepartmentAddress());
		dto.setDepartmentCode(domain.getDepartmentCode());
		dto.setStudentDTOs(studentTransformer.transformDomainToDTO(domain.getStudents()));
		return dto;
	}

	@Override
	public Department transformDTOToDomain(DepartmentDTO dto) throws TransformerException {
		Department domain = new Department();
		if (null != dto.getDepartmentId()) {
			domain.setDepartmentId(dto.getDepartmentId());
		}
		domain.setDepartmentName(dto.getDepartmentName());
		domain.setDepartmentAddress(dto.getDepartmentAddress());
		domain.setDepartmentCode(dto.getDepartmentCode());
		domain.setStudents(studentTransformer.transformDTOToDomain(dto.getStudentDTOs()));
		return domain;
	}

}
