package com.banking.cards.application.service.impl;

import com.banking.cards.application.avro.ApplicationDataAvro;
import com.banking.cards.application.entity.PersonalCardKey;
import com.banking.cards.application.entity.PersonalInformationEntity;
import com.banking.cards.application.handler.exception.ResourceNotFoundException;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class CardsApplicationServiceImpl implements CardsApplicationService {

    private final CardDetailsRepository cardDetailsRepository;
    private final PersonalInformationRepository personalInformationRepository;
    private final FinancialInformationRepository financialInformationRepository;

    @Override
    @Transactional
    public ApiResponse<TrackingID> createApplication(ApplicationRequest applicationRequest){
        verifyCardDetails(applicationRequest);
        if(verifyIfApplicationAlreadyProcessed(applicationRequest)){
            log.error("Card already processed for user: {} with correlationId: {}", applicationRequest.getPersonalInformation()
                    .getFullName(), applicationRequest.getPersonalInformation().getCorrelationId());
            throw new ResourceNotFoundException("Card already processed for user: "+applicationRequest.getPersonalInformation()
                    .getFullName() + "with correlation id: "+ applicationRequest.getPersonalInformation().getCorrelationId());
        }
        this.personalInformationRepository.save(CardsApplicationUtility.convertPersonalInformationToPersonalInformationEntity(applicationRequest));
        this.financialInformationRepository.save(CardsApplicationUtility.convertFinancialInformationToFinancialInformationEntity(applicationRequest));
        //Publish the data to Kafka Topic (Setup a docker for Kafka Cluster and Schema Registry for Avro)

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
                                        .getFullName(), applicationRequest.getPersonalInformation().getCorrelationId());
                     return new ResourceNotFoundException("Card details not found");
                });
    }
    @Override
    public TrackingResponse getApplication(TrackingID trackingId) throws ResourceNotFoundException {
        PersonalInformationEntity personalInformationEntity = this.personalInformationRepository
                .findByCorrelationId(trackingId.getTrackingID());
        return TrackingResponse.builder()
                .applicationStatus(personalInformationEntity.getApplicationStatus())
                .trackingId(personalInformationEntity.getCorrelationId())
                .socialSecurityNumber(personalInformationEntity.getPersonalCardKey().getSocialSecurityNumber())
                .validationMessages(personalInformationEntity.getComments())
                .build();
    }
}
