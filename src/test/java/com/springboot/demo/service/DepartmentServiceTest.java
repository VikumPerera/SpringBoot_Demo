package com.springboot.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.demo.domain.Department;
import com.springboot.demo.dto.DepartmentDTO;
import com.springboot.demo.exception.TransformerException;
import com.springboot.demo.repository.DepartmentRepository;
import com.springboot.demo.transformer.DepartmentTransformer;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentServiceTest {
	
	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	@MockBean
	private DepartmentTransformer departmentTransformer;
	
	@BeforeAll
	void setUp() throws TransformerException {
		Department department = Department.builder()
									.departmentId(1L)
									.departmentName("English")
									.build();
		DepartmentDTO departmentDTO = DepartmentDTO.builder()
									.departmentId(1L)
									.departmentName("English")
									.build();
		
		Mockito.when(departmentRepository.getDepartmentByName("English")).thenReturn(department);
		Mockito.when(departmentTransformer.transformDomainToDTO(department)).thenReturn(departmentDTO);
		
		
	}

	@Test
	@DisplayName("Get data based on valid department name")
	@Disabled
	void testGetDepartmentByName() throws TransformerException {
		String departmentName = "English";
		DepartmentDTO foundDTO = departmentService.getDepartmentByName(departmentName);
		assertEquals(departmentName, foundDTO.getDepartmentName());
	}

}
