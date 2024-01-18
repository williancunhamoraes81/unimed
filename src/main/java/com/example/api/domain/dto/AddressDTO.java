package com.example.api.domain.dto;

import com.example.api.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

	@NotEmpty(message = "Street required")
	private String street;

	@NotEmpty(message = "City required")
	private String city;

	@NotEmpty(message = "zipCode required")
	private String zipCode;

	@NotNull
	private Customer customer;
}
