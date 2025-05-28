package com.banking.cards.application.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInformation {
    @NotNull(message = "income details are mandatory")
    @Valid
    private IncomeDetails incomeDetails;

}
