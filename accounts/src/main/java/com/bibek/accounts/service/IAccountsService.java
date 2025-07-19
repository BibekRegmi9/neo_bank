package com.bibek.accounts.service;

import com.bibek.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto customer info
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);

}
