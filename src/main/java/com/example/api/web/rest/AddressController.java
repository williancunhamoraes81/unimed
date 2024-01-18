package com.example.api.web.rest;

import com.example.api.domain.dto.AddressDTO;
import com.example.api.domain.dto.CepBodyDTO;
import com.example.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

	private AddressService service;

	@Autowired
	public AddressController(AddressService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid AddressDTO addressDTO) {
		return ResponseEntity.ok().body(service.save(addressDTO));
	}

	@PostMapping("/cep")
	public ResponseEntity<Object> saveCep(@RequestBody @Valid CepBodyDTO cepBodyDTO) throws Exception {
		return ResponseEntity.ok().body(service.saveCep(cepBodyDTO));
	}

}
