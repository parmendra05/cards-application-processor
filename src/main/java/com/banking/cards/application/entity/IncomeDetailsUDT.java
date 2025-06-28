package com.banking.cards.application.entity;

import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UserDefinedType(value = "income_details")
@Builder
public class IncomeDetailsUDT {
    private double grossAnnualIncome;
    private double additionalIncome;
}
