package com.springboot.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.springboot.demo.domain.Department;
import com.springboot.demo.exception.TransformerException;
@DataJpaTest
class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@BeforeEach
	void setUp() throws TransformerException {
		Department department = Department.builder()
									.departmentName("English")
									.departmentCode("EN-001")
									.departmentAddress("Panadura")
									.build();
		testEntityManager.persist(department);
	}
	
	@Test
	void testFindById() {
		Department found = departmentRepository.findById(1L).get();
		assertEquals(found.getDepartmentName(), "English");
	}

}
