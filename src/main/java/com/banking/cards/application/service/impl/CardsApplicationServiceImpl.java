package com.banking.cards.application.service.impl;

import com.banking.cards.application.avro.ApplicationValidationResultEvent;
import com.banking.cards.application.entity.PersonalCardKey;
import com.banking.cards.application.entity.PersonalInformationEntity;
import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.kafka.publisher.CardsApplicationSubmitPublisher;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.banking.cards.application.model.response.ApiResponse;
import com.banking.cards.application.model.response.TrackingID;
import com.banking.cards.application.model.response.TrackingResponse;
import com.banking.cards.application.repository.CardDetailsRepository;
import com.banking.cards.application.repository.FinancialInformationRepository;
import com.banking.cards.application.repository.PersonalInformationRepository;
import com.banking.cards.application.service.CardsApplicationService;
import com.banking.cards.application.utility.CardsApplicationUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CardsApplicationServiceImpl implements CardsApplicationService {

    private final CardDetailsRepository cardDetailsRepository;
    private final PersonalInformationRepository personalInformationRepository;
    private final FinancialInformationRepository financialInformationRepository;
    private final CardsApplicationSubmitPublisher cardsApplicationSubmitPublisher;

    @Override
    @Transactional
    public ApiResponse<TrackingID> createApplication(ApplicationRequest applicationRequest){
        verifyCardDetails(applicationRequest);
        if(verifyIfApplicationAlreadyProcessed(applicationRequest)){
            log.error("Card already processed for user: {} with correlationId: {}", applicationRequest.getPersonalInformation()
                    .getFullName(), applicationRequest.getCorrelationId());
            throw new ResourceNotFoundException("Card already processed for user: "+applicationRequest.getPersonalInformation()
                    .getFullName() + "with correlation id: "+ applicationRequest.getCorrelationId());
        }
        this.personalInformationRepository.save(CardsApplicationUtility.convertPersonalInformationToPersonalInformationEntity(applicationRequest));
        this.financialInformationRepository.save(CardsApplicationUtility.convertFinancialInformationToFinancialInformationEntity(applicationRequest));
        this.cardsApplicationSubmitPublisher.publishApplicationSubmitEvent(CardsApplicationUtility.mapToApplicationDataAvro(applicationRequest));

        return ApiResponse.<TrackingID>builder()
                .data(TrackingID.builder().trackingID(applicationRequest.getCorrelationId()).build())
                .build();
    }

    private boolean verifyIfApplicationAlreadyProcessed(ApplicationRequest applicationRequest) {
        return this.personalInformationRepository.existsById(new PersonalCardKey(applicationRequest.getCardDetails().getCardType(),
                applicationRequest.getPersonalInformation().getSocialSecurityNumber()));
    }

    private void verifyCardDetails(ApplicationRequest applicationRequest) {
        this.cardDetailsRepository
                .findById(applicationRequest.getCardDetails().getCardId())
                .orElseThrow(() -> {
                        log.error("Card details not found for card id: {} for user: {} with correlationId: {}",
                                applicationRequest.getCardDetails().getCardId(), applicationRequest.getPersonalInformation()
                                        .getFullName(), applicationRequest.getCorrelationId());
                     return new ResourceNotFoundException("Card details not found");
                });
    }
    @Override
    public TrackingResponse getApplication(String trackingId) throws ResourceNotFoundException {
        PersonalInformationEntity personalInformationEntity = this.personalInformationRepository
                .findByCorrelationId(trackingId).get();
        return TrackingResponse.builder()
                .applicationStatus(personalInformationEntity.getApplicationStatus())
                .trackingId(personalInformationEntity.getCorrelationId())
                .socialSecurityNumber(personalInformationEntity.getPersonalCardKey().getSocialSecurityNumber())
                .validationMessages(personalInformationEntity.getComments())
                .build();
    }

    @Override
    public void updateApplication(ApplicationValidationResultEvent applicationValidationResultEvent) {
        String correlationID = applicationValidationResultEvent.getCorrelationID().toString();
        PersonalInformationEntity personalInformationEntity = getPersonalInformation(applicationValidationResultEvent.getCorrelationID().toString());
        updateApplicationStatus(personalInformationEntity, applicationValidationResultEvent);
    }

    private PersonalInformationEntity getPersonalInformation(String correlationID) {
        return this.personalInformationRepository.findByCorrelationId(correlationID).orElseThrow(() -> {
            log.error("No personal information found for correlation ID: {}", correlationID);
            throw new ResourceNotFoundException("No personal information found for correlation ID:"+correlationID);
        });
    }

    private void updateApplicationStatus(PersonalInformationEntity personalInformationEntity, ApplicationValidationResultEvent applicationValidationResultEvent) {
        personalInformationEntity.setApplicationStatus(applicationValidationResultEvent.getApplicationStatus().toString());
        personalInformationEntity.setComments("APPROVED".equals(applicationValidationResultEvent.getApplicationStatus()) ?
                 List.of("Credit Card application is approved successfully") :
                 applicationValidationResultEvent.getValidationMessages()
                         .stream()
                         .map(CharSequence::toString)
                         .toList());
        this.personalInformationRepository.save(personalInformationEntity);
        log.info("Successfully updated the application status of user : {} with correlation ID: {}",
                personalInformationEntity.getFullNameUDT(), applicationValidationResultEvent.getCorrelationID());
    }


}
