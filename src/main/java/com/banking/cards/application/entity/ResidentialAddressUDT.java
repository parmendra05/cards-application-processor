package com.banking.cards.application.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@UserDefinedType(value = "residential_address")
@Builder
public class ResidentialAddressUDT {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}