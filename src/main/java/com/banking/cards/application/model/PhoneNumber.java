package com.banking.cards.application.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {
    @NotBlank(message = "phone type is mandatory")
    private String type;
    @NotBlank(message = "phone number is mandatory")
    private String number;
}
