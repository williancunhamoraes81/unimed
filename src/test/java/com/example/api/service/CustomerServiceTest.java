package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDTO;
import com.example.api.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerService#findAll(Pageable)}
     */
    @Test
    void testFindAll() {
        PageImpl<Customer> pageImpl = new PageImpl<>(new ArrayList<>());
        when(customerRepository.findAllByOrderByNameAsc(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<Customer> actualFindAllResult = customerService.findAll(null);
        assertSame(pageImpl, actualFindAllResult);
        assertTrue(actualFindAllResult.toList().isEmpty());
        verify(customerRepository).findAllByOrderByNameAsc(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link CustomerService#save(CustomerDTO)}
     */
    @Test
    void testSave() {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setAddress(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setId(1L);
        customer2.setName("Name");

        Address address = new Address();
        address.setCity("Oxford");
        address.setCustomer(customer2);
        address.setId(1L);
        address.setStreet("Street");
        address.setZipcode("21654");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(address);
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setGender("Gender");
        customerDTO.setName("Name");
        assertSame(customer, customerService.save(customerDTO));
        verify(customerRepository).save(Mockito.<Customer>any());
    }

    /**
     * Method under test: {@link CustomerService#edit(Long, CustomerDTO)}
     */
    @Test
    void testEdit() {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setId(1L);
        customer2.setName("Name");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer3 = new Customer();
        customer3.setAddress(new ArrayList<>());
        customer3.setEmail("jane.doe@example.org");
        customer3.setGender("Gender");
        customer3.setId(1L);
        customer3.setName("Name");

        Address address = new Address();
        address.setCity("Oxford");
        address.setCustomer(customer3);
        address.setId(1L);
        address.setStreet("Street");
        address.setZipcode("21654");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(address);
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setGender("Gender");
        customerDTO.setName("Name");
        ResponseEntity<Object> actualEditResult = customerService.edit(1L, customerDTO);
        assertTrue(actualEditResult.hasBody());
        assertTrue(actualEditResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEditResult.getStatusCode());
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#edit(Long, CustomerDTO)}
     */
    @Test
    void testEdit2() {
        Customer customer = mock(Customer.class);
        doNothing().when(customer).setAddress(Mockito.<List<Address>>any());
        doNothing().when(customer).setEmail(Mockito.<String>any());
        doNothing().when(customer).setGender(Mockito.<String>any());
        doNothing().when(customer).setId(Mockito.<Long>any());
        doNothing().when(customer).setName(Mockito.<String>any());
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setId(1L);
        customer2.setName("Name");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer3 = new Customer();
        customer3.setAddress(new ArrayList<>());
        customer3.setEmail("jane.doe@example.org");
        customer3.setGender("Gender");
        customer3.setId(1L);
        customer3.setName("Name");

        Address address = new Address();
        address.setCity("Oxford");
        address.setCustomer(customer3);
        address.setId(1L);
        address.setStreet("Street");
        address.setZipcode("21654");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(address);
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setGender("Gender");
        customerDTO.setName("Name");
        ResponseEntity<Object> actualEditResult = customerService.edit(1L, customerDTO);
        assertTrue(actualEditResult.hasBody());
        assertTrue(actualEditResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEditResult.getStatusCode());
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customer).setAddress(Mockito.<List<Address>>any());
        verify(customer, atLeast(1)).setEmail(Mockito.<String>any());
        verify(customer, atLeast(1)).setGender(Mockito.<String>any());
        verify(customer).setId(Mockito.<Long>any());
        verify(customer, atLeast(1)).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#edit(Long, CustomerDTO)}
     */
    @Test
    void testEdit3() {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        Customer customer2 = mock(Customer.class);
        doNothing().when(customer2).setAddress(Mockito.<List<Address>>any());
        doNothing().when(customer2).setEmail(Mockito.<String>any());
        doNothing().when(customer2).setGender(Mockito.<String>any());
        doNothing().when(customer2).setId(Mockito.<Long>any());
        doNothing().when(customer2).setName(Mockito.<String>any());
        customer2.setAddress(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setGender("Gender");
        customer2.setId(1L);
        customer2.setName("Name");

        Customer customer3 = new Customer();
        customer3.setAddress(new ArrayList<>());
        customer3.setEmail("jane.doe@example.org");
        customer3.setGender("Gender");
        customer3.setId(1L);
        customer3.setName("Name");

        Address address = new Address();
        address.setCity("Oxford");
        address.setCustomer(customer3);
        address.setId(1L);
        address.setStreet("Street");
        address.setZipcode("21654");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(address);
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setGender("Gender");
        customerDTO.setName("Name");
        ResponseEntity<Object> actualEditResult = customerService.edit(1L, customerDTO);
        assertNull(actualEditResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualEditResult.getStatusCode());
        assertTrue(actualEditResult.getHeaders().isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customer2).setAddress(Mockito.<List<Address>>any());
        verify(customer2).setEmail(Mockito.<String>any());
        verify(customer2).setGender(Mockito.<String>any());
        verify(customer2).setId(Mockito.<Long>any());
        verify(customer2).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#remove(Long)}
     */
    @Test
    void testRemove() {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");
        Optional<Customer> ofResult = Optional.of(customer);
        doNothing().when(customerRepository).delete(Mockito.<Customer>any());
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ResponseEntity<Object> actualRemoveResult = customerService.remove(1L);
        assertNull(actualRemoveResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualRemoveResult.getStatusCode());
        assertTrue(actualRemoveResult.getHeaders().isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customerRepository).delete(Mockito.<Customer>any());
    }

    /**
     * Method under test: {@link CustomerService#remove(Long)}
     */
    @Test
    void testRemove2() {
        doNothing().when(customerRepository).delete(Mockito.<Customer>any());
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        ResponseEntity<Object> actualRemoveResult = customerService.remove(1L);
        assertNull(actualRemoveResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualRemoveResult.getStatusCode());
        assertTrue(actualRemoveResult.getHeaders().isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#findById(Long)}
     */
    @Test
    void testFindById() {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Customer> actualFindByIdResult = customerService.findById(1L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#findByFilters(String, String, String, String, Pageable)}
     */
    @Test
    void testFindByFilters() {
        PageImpl<Customer> pageImpl = new PageImpl<>(new ArrayList<>());
        when(customerRepository.findByFilters(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<Customer> actualFindByFiltersResult = customerService.findByFilters("Name", "jane.doe@example.org", "Gender",
                "Oxford", null);
        assertSame(pageImpl, actualFindByFiltersResult);
        assertTrue(actualFindByFiltersResult.toList().isEmpty());
        verify(customerRepository).findByFilters(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<Pageable>any());
    }
}

