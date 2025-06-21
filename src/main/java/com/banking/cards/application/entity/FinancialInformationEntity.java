package com.banking.cards.application.entity;

import com.banking.cards.application.model.IncomeDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "financial_information")
public class FinancialInformationEntity {
    @PrimaryKeyColumn(name = "social_security_number", type = PrimaryKeyType.PARTITIONED)
    private String socialSecurityNumber;
    @Column
    private IncomeDetailsUDT incomeDetailsUDT;

}
