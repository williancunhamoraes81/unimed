package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.dto.AddressDTO;
import com.example.api.domain.dto.CepBodyDTO;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressService.class})
@ExtendWith(SpringExtension.class)
class AddressServiceTest {
    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @MockBean
    private CustomerRepository customerRepository;

    /**
     * Method under test: {@link AddressService#save(AddressDTO)}
     */
    @Test
    void testSave2() {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");

        Address address = new Address();
        address.setCity("Oxford");
        address.setCustomer(customer);
        address.setId(1L);
        address.setStreet("Street");
        address.setZipcode("21654");
        when(addressRepository.save(Mockito.<Address>any())).thenReturn(address);

        Customer customer2 = new Customer();
        customer2.setAddress(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setId(1L);
        customer2.setName("Name");
        Optional<Customer> ofResult = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer3 = new Customer();
        customer3.setAddress(new ArrayList<>());
        customer3.setEmail("jane.doe@example.org");
        customer3.setGender("Gender");
        customer3.setId(1L);
        customer3.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCustomer(customer3);
        ResponseEntity<Object> actualSaveResult = addressService.save(addressDTO);
        assertTrue(actualSaveResult.hasBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualSaveResult.getStatusCode());
        verify(addressRepository).save(Mockito.<Address>any());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AddressService#save(AddressDTO)}
     */
    @Test
    void testSave3() {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");

        Address address = new Address();
        address.setCity("Oxford");
        address.setCustomer(customer);
        address.setId(1L);
        address.setStreet("Street");
        address.setZipcode("21654");
        when(addressRepository.save(Mockito.<Address>any())).thenReturn(address);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        Customer customer2 = new Customer();
        customer2.setAddress(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setId(1L);
        customer2.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCustomer(customer2);
        ResponseEntity<Object> actualSaveResult = addressService.save(addressDTO);
        assertEquals("Customer not found.", actualSaveResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualSaveResult.getStatusCode());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

}

