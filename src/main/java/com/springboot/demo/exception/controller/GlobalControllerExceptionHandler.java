package com.springboot.demo.exception.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.demo.dto.response.MessageDTO;
import com.springboot.demo.dto.response.ResultsDTO;
import com.springboot.demo.enums.ResultStatus;
import com.springboot.demo.exception.TransformerException;

@ControllerAdvice
public class GlobalControllerExceptionHandler{
	
	private static final String TRANSFORMER_EXCEPTION = "Error transforming object";
	
	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<ResultsDTO> handleEntityNotFoundExceptions(Exception ex, HttpServletResponse httpServletResponse) {
		ResultsDTO response = new ResultsDTO();
		response.setMessage(new MessageDTO(ex.getMessage()));
		response.setResultStatus(ResultStatus.FAILED);
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setHttpCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { TransformerException.class })
	protected ResponseEntity<ResultsDTO> handleTransformerException(Exception ex, HttpServletResponse httpServletResponse) {
		ResultsDTO response = new ResultsDTO();
		response.setMessage(new MessageDTO(TRANSFORMER_EXCEPTION));
		response.setResultStatus(ResultStatus.FAILED);
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setHttpCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
