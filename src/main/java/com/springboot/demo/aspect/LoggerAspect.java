package com.springboot.demo.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class LoggerAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
	@Autowired
    private ObjectMapper mapper;
	
	
	@Pointcut("within(com.springboot.demo.controller..*)  || "
			+ "within(com.springboot.demo.service..*)")
	public void beforeExecutionLogger() {
		
	}
	
	@Before("beforeExecutionLogger()")
    public void logMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Map<String, Object> parameters = getParameters(joinPoint);

        try {
        	LOGGER.info("==> method(s): {}, arguments: {} ",
        			signature.getMethod(), mapper.writeValueAsString(parameters));
        } catch (JsonProcessingException e) {
        	LOGGER.error("Error while converting", e);
        }
    }

	private Map<String, Object> getParameters(JoinPoint joinPoint) {
        
		CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        HashMap<String, Object> map = new HashMap<>();

        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        return map;
    }
}
