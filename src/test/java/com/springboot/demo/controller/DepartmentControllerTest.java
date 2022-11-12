package com.springboot.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springboot.demo.dto.DepartmentDTO;
import com.springboot.demo.exception.TransformerException;
import com.springboot.demo.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService departmentService;
	
	private DepartmentDTO inputDepartmentDTO;
	private DepartmentDTO outputDepartmentDTO;
	
	
	@BeforeEach
	void setUp() throws TransformerException {
		
		inputDepartmentDTO = DepartmentDTO.builder()
				.departmentName("ENGLISH")
				.departmentCode("EN-001")
				.departmentAddress("Panadura")
				.build();
		
		outputDepartmentDTO = DepartmentDTO.builder()
				.departmentId(1L)
				.departmentName("ENGLISH")
				.departmentCode("EN-001")
				.departmentAddress("Panadura")
				.build();
	}
	
	
	@Test
	void saveDepartmentTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/departments")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"departmentName\":\"ART\",\n"
						+ "    \"departmentAddress\":\"University of Kelaniya\",\n"
						+ "    \"departmentCode\":\"DEP002\",\n"
						+ "    \"studentDTOs\":[\n"
						+ "       {\n"
						+ "           \"studentName\":\"Vikum\",\n"
						+ "           \"studentEmail\":\"vikumchathuranga92@gma\",\n"
						+ "           \"studentCode\":\"ST001\"\n"
						+ "       },\n"
						+ "       {\n"
						+ "           \"studentName\":\"Madumila\",\n"
						+ "           \"studentEmail\":\"madumila@gmail.com\",\n"
						+ "           \"studentCode\":\"ST001\"\n"
						+ "       }\n"
						+ "   ]\n"
						+ "}"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	void getDepartmentByIdTest() throws Exception {
		
		Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(outputDepartmentDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.payload.departmentName").value(outputDepartmentDTO.getDepartmentName()));
	}

}
