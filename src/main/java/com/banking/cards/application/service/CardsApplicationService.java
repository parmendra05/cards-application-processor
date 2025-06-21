package com.banking.cards.application.service;

import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.banking.cards.application.model.response.ApiResponse;

import java.util.UUID;

public interface CardsApplicationService {
    ApiResponse<UUID> createApplication(ApplicationRequest applicationRequest);
}
