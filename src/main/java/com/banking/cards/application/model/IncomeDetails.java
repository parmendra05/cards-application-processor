package com.banking.cards.application.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDetails {
    @Min(value = 180000, message = "gross annual income must be equal or greater than 180000")
    private double grossAnnualIncome;
    private double additionalIncome;
}
