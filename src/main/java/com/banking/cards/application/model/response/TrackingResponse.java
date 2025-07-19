package com.banking.cards.application.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrackingResponse {
    private String trackingId;
    private String applicationStatus;
    private String socialSecurityNumber;
    private List<String> validationMessages;
}
