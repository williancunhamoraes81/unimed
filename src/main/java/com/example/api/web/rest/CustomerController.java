package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDTO;
import com.example.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid CustomerDTO customerDTO) {
		return ResponseEntity.ok().body(service.save(customerDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> edit(@RequestBody @Valid CustomerDTO customerDTO,
									   @PathVariable Long id) {
		return ResponseEntity.ok().body(service.edit(id, customerDTO));
	}

	@GetMapping
	public Page<Customer> findAll(@RequestParam(defaultValue = "0") int page,
								  @RequestParam(defaultValue = "5") int size) {
		return service.findAll(PageRequest.of(page, size));
	}

	@GetMapping("/search")
	public Page<Customer> findByFilters(@RequestParam(defaultValue = "0") int page,
										@RequestParam(defaultValue = "5") int size,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "gender", required = false) String gender,
			@RequestParam(name = "city", required = false) String city) {
		return service.findByFilters(name, email, gender, city, PageRequest.of(page, size));
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		return service.remove(id);
	}

}
