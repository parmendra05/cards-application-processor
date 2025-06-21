package com.banking.cards.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@Data
@AllArgsConstructor
@PrimaryKeyClass
public class PersonalCardKey {
    @PrimaryKeyColumn(name = "card_type")
    private String cardType;
    @PrimaryKeyColumn(name = "social_security_number" , type = PrimaryKeyType.PARTITIONED)
    private String socialSecurityNumber;
}
