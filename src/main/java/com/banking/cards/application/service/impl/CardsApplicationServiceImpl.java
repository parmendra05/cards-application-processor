package com.banking.cards.application.service.impl;

import com.banking.cards.application.entity.FullNameUDT;
import com.banking.cards.application.entity.PersonalCardKey;
import com.banking.cards.application.entity.PersonalInformationEntity;
import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.banking.cards.application.model.response.ApiResponse;
import com.banking.cards.application.repository.CardDetailsRepository;
import com.banking.cards.application.repository.PersonalInformationRepository;
import com.banking.cards.application.service.CardsApplicationService;
import com.banking.cards.application.utility.CardsApplicationUtility;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardsApplicationServiceImpl implements CardsApplicationService {

    private final CardDetailsRepository cardDetailsRepository;
    private final PersonalInformationRepository personalInformationRepository;

    @Override
    public ApiResponse<UUID> createApplication(ApplicationRequest applicationRequest){
        //Verify the card details
        this.cardDetailsRepository
                .findById(applicationRequest.getCardDetails().getCardId())
                .orElseThrow(() -> {
                        log.error("Card details not found for card id: {} for user: {} with correlationId: {}",
                                applicationRequest.getCardDetails().getCardId(), applicationRequest.getPersonalInformation()
                                        .getFullName(), applicationRequest.getPersonalInformation().getCorrelationId());
                     return new ResourceNotFoundException("Card details not found");
                });
        // Check if the card is already processed for user
       Boolean isAlreadyProcessed = this.personalInformationRepository.existsById(new PersonalCardKey(applicationRequest.getPersonalInformation()
                .getSocialSecurityNumber(), applicationRequest.getCardDetails().getCardId()));
        if(isAlreadyProcessed){
            log.error("Card already processed for user: {} with correlationId: {}", applicationRequest.getPersonalInformation()
                    .getFullName(), applicationRequest.getPersonalInformation().getCorrelationId());
            throw new ResourceNotFoundException("Card already processed for user: "+applicationRequest.getPersonalInformation()
                    .getFullName() + "with correlation id: "+ applicationRequest.getPersonalInformation().getCorrelationId());
        }
        this.personalInformationRepository.save(CardsApplicationUtility
                .convertPersonalInformationToPersonalInformationEntity(
                        applicationRequest.getPersonalInformation()));
        return null;
    }
}
