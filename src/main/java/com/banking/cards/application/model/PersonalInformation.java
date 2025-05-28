package com.banking.cards.application.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PersonalInformation {
    @Valid
    private FullName fullName;
    @NotBlank(message = "date of birth is mandatory")
    private String dateOfBirth;
    @NotBlank(message = "social security number is mandatory")
    private String socialSecurityNumber;
    @Valid
    private ContactInformation contactInformation;

}
