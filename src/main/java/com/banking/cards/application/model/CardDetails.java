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
public class CardDetails {
    @NotBlank(message = "card-id is mandatory")
    private String cardId;
    @NotBlank(message = "card-type is mandatory")
    private String cardType;
}
