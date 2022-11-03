package com.springboot.demo.dto;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	private Long studentId;
	private String studentName;
	@Email
	private String studentEmail;
	private String studentCode;
}
