package com.banking.cards.application.entity;

import com.banking.cards.application.model.ContactInformation;
import com.banking.cards.application.model.FullName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(value = "personal_information")
public class PersonalInformationEntity {
    @PrimaryKey
    private PersonalCardKey personalCardKey;
    @Column
    private FullNameUDT fullNameUDT;
    private String dateOfBirth;
    @Column
    private ResidentialAddressUDT residentialAddressUDT;
    @Column
    private List<PhoneNumberUDT> phoneNumbers;
    @Column
    private String status;
}
