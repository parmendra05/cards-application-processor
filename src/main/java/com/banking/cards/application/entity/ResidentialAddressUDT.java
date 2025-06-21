package com.banking.cards.application.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@UserDefinedType(value = "residential_address")
public class ResidentialAddressUDT {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}