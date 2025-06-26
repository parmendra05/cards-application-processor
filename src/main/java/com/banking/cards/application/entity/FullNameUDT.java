package com.banking.cards.application.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@UserDefinedType(value = "full_name")
@Builder
public class FullNameUDT {
    private String firstName;
    private String middleName;
    private String lastName;
}
