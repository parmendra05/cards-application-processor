package com.banking.cards.application.api;

import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.banking.cards.application.model.response.ApiResponse;
import com.banking.cards.application.model.response.TrackingID;
import com.banking.cards.application.model.response.TrackingResponse;
import com.banking.cards.application.service.CardsApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("v1/card-applications")
@Slf4j
@RequiredArgsConstructor
public class CardsApplicationAPI {

    private final CardsApplicationService cardsApplicationService;

    @PostMapping
    public ResponseEntity<ApiResponse<TrackingID>>  createApplication(@RequestBody @Valid ApplicationRequest applicationRequest, HttpServletRequest request) {
        applicationRequest.setCorrelationId((String) request.getAttribute("correlationId"));
        // Write a logger logging about application start process, with user name and correlation
        return new ResponseEntity<ApiResponse<TrackingID>>(this.cardsApplicationService.createApplication(applicationRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<TrackingResponse>> getApplication(@RequestBody TrackingID trackingID) {
        return ResponseEntity.ok(ApiResponse.<TrackingResponse>builder()
                                    .data(this.cardsApplicationService.getApplication(trackingID))
                                    .build());
    }

}