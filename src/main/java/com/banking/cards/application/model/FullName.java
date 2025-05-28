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
public class FullName {
    @NotBlank(message = "first-name is mandatory")
    private String firstName;
    private String middleName;
    @NotBlank(message = "last-name is mandatory")
    private String lastName;
}
