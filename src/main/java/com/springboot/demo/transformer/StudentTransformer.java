package com.springboot.demo.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.demo.domain.Student;
import com.springboot.demo.dto.StudentDTO;
import com.springboot.demo.exception.TransformerException;

@Component
public class StudentTransformer extends AbstractTransformer<Student, StudentDTO> {

	@Override
	public StudentDTO transformDomainToDTO(Student domain) throws TransformerException {
		StudentDTO dto = new StudentDTO();
		dto.setStudentId(domain.getStudentId());
		dto.setStudentName(domain.getStudentName());
		dto.setStudentEmail(domain.getStudentEmail());
		dto.setStudentCode(domain.getStudentCode());
		return dto;
	}

	@Override
	public Student transformDTOToDomain(StudentDTO dto) throws TransformerException {
		Student domain = new Student();
		if (null != dto.getStudentId()) {
			domain.setStudentId(dto.getStudentId());
		}
		domain.setStudentName(dto.getStudentName());
		domain.setStudentEmail(dto.getStudentEmail());
		domain.setStudentCode(dto.getStudentCode());
		return domain;
	}
	
	@Override
    public List<StudentDTO> transformDomainToDTO(List<Student> domains) throws TransformerException {
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student domain : domains) {
            dtos.add(transformDomainToDTO(domain));
        }
        return dtos;
    }

    @Override
    public List<Student> transformDTOToDomain(List<StudentDTO> dtos) throws TransformerException {
        List<Student> domains = new ArrayList<>();
        for (StudentDTO dto : dtos) {
            domains.add(transformDTOToDomain(dto));
        }
        return domains;
    }

}
