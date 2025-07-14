package com.banking.cards.application.service;

import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.banking.cards.application.model.response.ApiResponse;
import com.banking.cards.application.model.response.TrackingID;
import com.banking.cards.application.model.response.TrackingResponse;

import java.util.UUID;

public interface CardsApplicationService {
    ApiResponse<TrackingID> createApplication(ApplicationRequest applicationRequest);
    TrackingResponse getApplication(String trackingId) throws ResourceNotFoundException;
}
