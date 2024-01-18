package com.example.api.domain.dto;

import com.example.api.domain.Address;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerDTO {

	@NotEmpty(message = "Name required")
	private String name;

	@NotEmpty(message = "Email required")
	@Email
	private String email;

	@NotEmpty(message = "Gender required")
	private String gender;

	private Address address;

}
