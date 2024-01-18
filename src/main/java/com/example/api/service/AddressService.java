package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.dto.AddressDTO;
import com.example.api.domain.dto.CepBodyDTO;
import com.example.api.domain.dto.CepDTO;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressService {

	private AddressRepository repository;
	private CustomerRepository customerRepository;
	private static final String BASE_URL = "https://viacep.com.br/ws/";

	@Autowired
	public AddressService(AddressRepository repository,
						  CustomerRepository customerRepository
	) {
		this.repository = repository;
		this.customerRepository = customerRepository;
	}


	public ResponseEntity<Object> save(AddressDTO addressDTO){
		Optional<Customer> customerOptional = customerRepository.findById(addressDTO.getCustomer().getId());
		if (customerOptional.isPresent()){
			return new ResponseEntity<>(repository.save(
					buildAddress(addressDTO.getCity(),
							addressDTO.getStreet(),
							addressDTO.getZipCode(),
							customerOptional.get())), HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>("Customer not found.", HttpStatus.BAD_REQUEST);
		}
	}

	private Address buildAddress(String city, String street, String zipCode, Customer customer){
		return Address.builder()
				.city(city)
				.street(street)
				.zipcode(zipCode)
				.customer(customer)
				.build();
	}

	public ResponseEntity<Object> saveCep(CepBodyDTO cepBodyDTO) throws Exception {
		CepDTO cepDTO = consultarCEP(cepBodyDTO.getCep());
		if(Objects.nonNull(cepDTO)){
			Optional<Customer> customerOptional = customerRepository.findById(cepBodyDTO.getCustomer());
			if (customerOptional.isPresent()){
				return new ResponseEntity<>(repository.save(
						buildAddress(cepDTO.getLocalidade(),
								cepDTO.getLogradouro(),
								cepBodyDTO.getCep(),
								customerOptional.get())), HttpStatus.CREATED);
			}else{
				return new ResponseEntity<>("Customer not found.", HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<>("Cep not found or incorrect.", HttpStatus.BAD_REQUEST);
		}
	}

	private CepDTO consultarCEP(String cep) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL + cep + "/json/"))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() == 200) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(response.body(), CepDTO.class);
		} else {
			return null;
		}
	}


}
