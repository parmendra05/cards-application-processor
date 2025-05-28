package com.banking.cards.application.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentialAddress {
    @NotBlank(message = "street is mandatory")
    private String street;
    @NotBlank(message = "city is mandatory")
    private String city;
    @NotBlank(message = "state is mandatory")
    private String state;
    @NotBlank(message = "zipCode is mandatory")
    private String zipCode;
}