package com.banking.cards.application.entity;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@UserDefinedType(value = "income_details")
public class IncomeDetailsUDT {
    private double grossAnnualIncome;
    private double additionalIncome;
}
