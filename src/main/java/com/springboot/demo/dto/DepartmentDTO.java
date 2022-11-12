package com.springboot.demo.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {
	
	private Long departmentId;
	@NotBlank(message = "DepartmentName cannot be null...")
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	@Valid
	@NotEmpty(message = "StudentDTO cannot be empty...")
	private List<StudentDTO> studentDTOs;
}
