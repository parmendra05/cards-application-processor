package com.banking.cards.application.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrackingResponse {
    private String trackingId;
    private String applicationStatus;
    private String socialSecurityNumber;
    private List<String> validationMessages;
}
