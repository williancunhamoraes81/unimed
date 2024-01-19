package com.example.api.web.rest;

import static org.mockito.Mockito.when;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.AddressDTO;
import com.example.api.domain.dto.CepBodyDTO;
import com.example.api.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@ContextConfiguration(classes = {AddressController.class})
@ExtendWith(SpringExtension.class)
class AddressControllerTest {
    @Autowired
    private AddressController addressController;

    @MockBean
    private AddressService addressService;

    /**
     * Method under test: {@link AddressController#save(AddressDTO)}
     */
    @Test
    void testSave() throws Exception {
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setGender("Gender");
        customer.setId(1L);
        customer.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Oxford");
        addressDTO.setCustomer(customer);
        addressDTO.setStreet("Street");
        addressDTO.setZipCode("21654");
        String content = (new ObjectMapper()).writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link AddressController#saveCep(CepBodyDTO)}
     */
    @Test
    void testSaveCep2() throws Exception {
        when(addressService.saveCep(Mockito.<CepBodyDTO>any())).thenReturn(null);

        CepBodyDTO cepBodyDTO = new CepBodyDTO();
        cepBodyDTO.setCep("Cep");
        cepBodyDTO.setCustomer(1L);
        String content = (new ObjectMapper()).writeValueAsString(cepBodyDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/address/cep")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AddressController#saveCep(String)}
     */
    @Test
    void testSaveCep4() throws Exception {
        when(addressService.getCep(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address/cep/{cep}", "Cep");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

