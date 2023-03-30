package com.blogApplication.blogDemo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resoureName,String fieldName,long fieldValue)
	{
		super(String.format("%s not found with %s : %s", resoureName,fieldName,fieldValue));
	 this.resourceName = resoureName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	

}
