package com.bibek.accounts.service.impl;

import com.bibek.accounts.constants.AccountsConstants;
import com.bibek.accounts.dto.AccountsDto;
import com.bibek.accounts.dto.CustomerDto;
import com.bibek.accounts.entity.Accounts;
import com.bibek.accounts.entity.Customer;
import com.bibek.accounts.exception.CustomerAlreadyExistsException;
import com.bibek.accounts.exception.ResourceNotFoundException;
import com.bibek.accounts.mapper.AccountsMapper;
import com.bibek.accounts.mapper.CustomerMapper;
import com.bibek.accounts.repository.AccountsRepo;
import com.bibek.accounts.repository.CustomerRepo;
import com.bibek.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;
    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto customer info
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.dtoToEntity(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number: " + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDate.now());
        customer.setUpdatedAt(LocalDate.now());
        customer.setCreatedBy("Bibek");
        Customer savedCustomer = customerRepo.save(customer);
        accountsRepo.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer =  customerRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.entityToDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.entityToDto(accounts, new AccountsDto()));
        return customerDto;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDate.now());
        newAccount.setCreatedBy("Bibek");
        return newAccount;
    }

    public boolean updateAccount(CustomerDto customerDto){
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.dtoToEntity(accountsDto, accounts);
            accounts = accountsRepo.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepo.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.dtoToEntity(customerDto,customer);
            customerRepo.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());
        return true;
    }

}
