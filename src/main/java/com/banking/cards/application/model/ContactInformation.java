package com.banking.cards.application.model;

// ContactInformation.java

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {
    @Valid
    private ResidentialAddress residentialAddress;
    @NotEmpty(message = "phone number cannot be empty")
    @NotNull(message = "phone number cannot be null")
    @Valid
    private List<PhoneNumber> phoneNumbers;


}
