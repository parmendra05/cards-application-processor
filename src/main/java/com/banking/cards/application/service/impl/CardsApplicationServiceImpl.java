package com.banking.cards.application.service.impl;

import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.banking.cards.application.model.response.ApiResponse;
import com.banking.cards.application.repository.CardDetailsRepository;
import com.banking.cards.application.service.CardsApplicationService;
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

    @Override
    public ApiResponse<UUID> createApplication(ApplicationRequest applicationRequest){
        this.cardDetailsRepository
                .findById(applicationRequest.getCardDetails().getCardId())
                .orElseThrow(() -> {
                        log.error("Card details not found for card id: {}", applicationRequest.getCardDetails().getCardId());
                     return new ResourceNotFoundException("Card details not found");
                });
        return  null;
    }
}
