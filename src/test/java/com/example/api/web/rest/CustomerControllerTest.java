package com.example.api.web.rest;

import static org.mockito.Mockito.when;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDTO;
import com.example.api.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerController#edit(CustomerDTO, Long)}
     */
    @Test
    void testEdit2() throws Exception {
        when(customerService.edit(Mockito.<Long>any(), Mockito.<CustomerDTO>any()))
                .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

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

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(address);
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setGender("Gender");
        customerDTO.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(customerDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link CustomerController#findAll(int, int)}
     */
    @Test
    void testFindAll3() throws Exception {
        when(customerService.findAll(Mockito.<Pageable>any()))
                .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customers");
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link CustomerController#findByFilters(int, int, String, String, String, String)}
     */
    @Test
    void testFindByFilters3() throws Exception {
        when(customerService.findByFilters(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<Pageable>any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customers/search");
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link CustomerController#findById(Long)}
     */
    @Test
    void testFindById() throws Exception {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerService.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}", 1L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"gender\":\"Gender\",\"address\":[]}"));
    }

    /**
     * Method under test: {@link CustomerController#findById(Long)}
     */
    @Test
    void testFindById2() throws Exception {
        when(customerService.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CustomerController#findById(Long)}
     */
    @Test
    void testFindById3() throws Exception {
        when(customerService.findById(Mockito.<Long>any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link CustomerController#remove(Long)}
     */
    @Test
    void testRemove() throws Exception {
        when(customerService.remove(Mockito.<Long>any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customers/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link CustomerController#remove(Long)}
     */
    @Test
    void testRemove2() throws Exception {
        when(customerService.remove(Mockito.<Long>any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customers/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link CustomerController#save(CustomerDTO)}
     */
    @Test
    void testSave3() throws Exception {
        when(customerService.findAll(Mockito.<Pageable>any()))
                .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

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

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(address);
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setGender("Gender");
        customerDTO.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(customerDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

