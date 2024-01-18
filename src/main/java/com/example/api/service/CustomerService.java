package com.example.api.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.api.domain.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public Page<Customer> findAll(Pageable pageable) {
		return repository.findAllByOrderByNameAsc(pageable);
	}

	public Customer save(CustomerDTO customerDTO){
		return repository.save(Customer.builder()
						.email(customerDTO.getEmail())
						.gender(customerDTO.getGender())
						.name(customerDTO.getName())
				.build());
	}

	public ResponseEntity<Object> edit(Long id, CustomerDTO customerDTO){
		Optional<Customer> customerOptional = repository.findById(id);
		if(customerOptional.isPresent()){
			Customer customer = customerOptional.get();
			if(Objects.nonNull(customerDTO.getName())){
				customer.setName(customerDTO.getName());
			}
			if(Objects.nonNull(customerDTO.getEmail())){
				customer.setEmail(customerDTO.getEmail());
			}
			if(Objects.nonNull(customerDTO.getGender())){
				customer.setGender(customerDTO.getGender());
			}
			return ResponseEntity.ok().body(repository.save(customer));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<Object> remove(Long id){
		Optional<Customer> customerOptional = repository.findById(id);
		if(customerOptional.isPresent()){
			repository.delete(customerOptional.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public Page<Customer> findByFilters(String name, String email, String gender, String city, Pageable pageable) {
		return repository.findByFilters(name, email, gender, city, pageable);
	}

}
