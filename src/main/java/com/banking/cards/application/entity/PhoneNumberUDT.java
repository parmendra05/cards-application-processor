package com.banking.cards.application.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UserDefinedType(value = "phone_number")
@Builder
public class PhoneNumberUDT {
    private String type;
    private String number;
}
