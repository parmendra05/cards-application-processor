package com.banking.cards.application.utility;

import com.banking.cards.application.avro.ApplicationDataAvro;
import com.banking.cards.application.entity.*;
import com.banking.cards.application.model.FinancialInformation;
import com.banking.cards.application.model.PersonalInformation;
import com.banking.cards.application.model.request.ApplicationRequest;

import java.util.List;
import java.util.stream.Collectors;

public class CardsApplicationUtility {
    public static PersonalInformationEntity convertPersonalInformationToPersonalInformationEntity(ApplicationRequest applicationRequest) {
        PersonalInformation personalInformation = applicationRequest.getPersonalInformation();
        return PersonalInformationEntity.builder()
                .personalCardKey(new PersonalCardKey(applicationRequest.getCardDetails().getCardType(), personalInformation.getSocialSecurityNumber()))
                .fullNameUDT(convertFullNameToFullNameUDT(personalInformation))
                .dateOfBirth(personalInformation.getDateOfBirth())
                .residentialAddressUDT(convertResidentialAddressToResidentialAddressUDT(personalInformation))
                .phoneNumbers(convertListOfPhoneNumbersToListOfPhoneNumberUDT(personalInformation))
                .correlationId(personalInformation.getCorrelationId())
                .applicationStatus("PENDING")
                .build();
    }

    private static List<PhoneNumberUDT> convertListOfPhoneNumbersToListOfPhoneNumberUDT(PersonalInformation personalInformation) {
        return personalInformation.getContactInformation().getPhoneNumbers().stream()
                .map(phoneNumber ->
                        PhoneNumberUDT.builder()
                                .number(phoneNumber.getNumber())
                                .type(phoneNumber.getType())
                                .build()
                ).collect(Collectors.toList());
    }

    private static ResidentialAddressUDT convertResidentialAddressToResidentialAddressUDT(PersonalInformation personalInformation) {
        return ResidentialAddressUDT.builder()
                .state(personalInformation.getContactInformation().getResidentialAddress().getState())
                .city(personalInformation.getContactInformation().getResidentialAddress().getCity())
                .street(personalInformation.getContactInformation().getResidentialAddress().getStreet())
                .zipCode(personalInformation.getContactInformation().getResidentialAddress().getZipCode())
                .build();
    }

    private static FullNameUDT convertFullNameToFullNameUDT(PersonalInformation personalInformation) {
        return FullNameUDT.builder().firstName(personalInformation.getFullName().getFirstName())
                .middleName(personalInformation.getFullName().getMiddleName())
                .lastName(personalInformation.getFullName().getLastName())
                .build();
    }

    public static FinancialInformationEntity convertFinancialInformationToFinancialInformationEntity(ApplicationRequest applicationRequest) {
        FinancialInformation financialInformation = applicationRequest.getFinancialInformation();
        return FinancialInformationEntity.builder()
             .socialSecurityNumber(applicationRequest.getPersonalInformation().getSocialSecurityNumber())
             .incomeDetailsUDT(IncomeDetailsUDT.builder()
                     .additionalIncome(financialInformation.getIncomeDetails().getAdditionalIncome())
                     .grossAnnualIncome(financialInformation.getIncomeDetails().getGrossAnnualIncome())
                     .build())
             .build();
    }

    public static ApplicationDataAvro mapToApplicationDataAvro(ApplicationRequest applicationRequest){
        return ApplicationDataAvro.newBuilder()
                .setCardType(applicationRequest.getCardDetails().getCardType())
                .setCorrelationID(applicationRequest.getCorrelationId())
                .setDateOfBirth(applicationRequest.getPersonalInformation().getDateOfBirth())
                .setSocialSecurityNumber(applicationRequest.getPersonalInformation().getSocialSecurityNumber())
                .setGrossAnnualIncome(applicationRequest.getFinancialInformation().getIncomeDetails().getGrossAnnualIncome())
                .build();
    }
}
