package com.banking.cards.application.model.request;

import com.banking.cards.application.model.CardDetails;
import com.banking.cards.application.model.ContactInformation;
import com.banking.cards.application.model.FinancialInformation;
import com.banking.cards.application.model.PersonalInformation;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class ApplicationRequest {

    @NonNull
    @Valid
    private CardDetails cardDetails;

    @NonNull
    private PersonalInformation personalInformation;

    @NonNull
    private FinancialInformation financialInformation;


}
