package com.banking.cards.application.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackingID {
    private String trackingID;
}
