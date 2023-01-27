package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.repository.CustomerRepository;
import com.ecommerce.library.repository.RoleRepository;
import com.ecommerce.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPassword(customerDto.getPassword());
        customer.setUsername(customerDto.getUsername());

        customer.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));

        Customer customerSave = customerRepository.save(customer);

        return mapperDTO(customerSave);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    private CustomerDto mapperDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customerDto.getLastName());
        customerDto.setUsername(customerDto.getUsername());
        customerDto.setPassword(customerDto.getPassword());
        return customerDto;
    }
}
