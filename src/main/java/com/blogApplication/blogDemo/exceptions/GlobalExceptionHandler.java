package com.blogApplication.blogDemo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.blogApplication.blogDemo.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	 String message = ex.getMessage();
	 ApiResponse apiResponse = new ApiResponse(message,false);
	 return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
 }
	@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> methodNotValidExceptionHandler(MethodArgumentNotValidException ex)
{
		Map<String, String> respMap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			respMap.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(respMap,HttpStatus.BAD_REQUEST);
}
}
