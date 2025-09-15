package com.bibek.accounts.service.impl;

import com.bibek.accounts.dto.CustomerDto;
import com.bibek.accounts.entity.Accounts;
import com.bibek.accounts.entity.Customer;
import com.bibek.accounts.exception.CustomerAlreadyExistsException;
import com.bibek.accounts.mapper.CustomerMapper;
import com.bibek.accounts.repository.AccountsRepo;
import com.bibek.accounts.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Account Service Implementation Unit Test")
class AccountServiceImplTest {

    @Mock
    private AccountsRepo accountsRepo;
    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private AccountServiceImpl accountService;


    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto.setName("John");
        customerDto.setMobileNumber("1234567890");
        customerDto.setEmail("jogn@gmail.com");
    }

    @Nested
    @DisplayName("Should create a new account when customer does not exist")
    class AccountServiceTest {

        @Test
        @DisplayName("Should create a new account")
        void createAccountSuccess() {
            //Given

            Customer newCustomer = CustomerMapper.dtoToEntity(customerDto, new Customer());
            when(customerRepo.findByMobileNumber("1234567890"))
                    .thenReturn(Optional.empty());
            when(customerRepo.save(any(Customer.class)))
                    .thenReturn(newCustomer);
            when(accountsRepo.save(any(Accounts.class)))
                    .thenReturn(new Accounts());

            //When
            accountService.createAccount(customerDto);

            //Then
            verify(customerRepo, times(1)).findByMobileNumber("1234567890");
            verify(customerRepo, times(1)).save(any(Customer.class));
            verify(accountsRepo, times(1)).save(any(Accounts.class));
        }


//        @Test
//        @DisplayName("Should throw exception when customer already exists")
//        void createAccount_Failure_AlreadyExists() {
//            // Given
//            when(customerRepo.findByMobileNumber("1234567890"))
//                    .thenReturn(Optional.of(customer));
//
//            // When + Then
//            assertThrows(CustomerAlreadyExistsException.class,
//                    () -> accountService.createAccount(customerDto));
//
//            verify(customerRepo, times(1)).findByMobileNumber("1234567890");
//            verify(customerRepo, never()).save(any(Customer.class));
//            verify(accountsRepo, never()).save(any(Accounts.class));
//        }

    }

}