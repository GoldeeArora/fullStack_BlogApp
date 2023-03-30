package com.blogApplication.blogDemo.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min = 4)
	private String categorytitle;
	@NotBlank
	@Size(min = 10)
	private String categoryDescription;
	

}
