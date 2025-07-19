package com.bibek.accounts.mapper;

import com.bibek.accounts.dto.CustomerDto;
import com.bibek.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto entityToDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer dtoToEntity(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}