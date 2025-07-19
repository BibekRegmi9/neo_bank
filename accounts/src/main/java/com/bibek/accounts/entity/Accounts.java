package com.bibek.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity{
    @Id
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "account_type")
    private String accountType;
    private String branchAddress;

}
