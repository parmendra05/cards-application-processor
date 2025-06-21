package com.banking.cards.application.api;

import com.banking.cards.application.handler.exception.ResourceNotFoundException;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.banking.cards.application.service.CardsApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("v1/card-applications")
@Slf4j
@RequiredArgsConstructor
public class CardsApplicationAPI {

    private final CardsApplicationService cardsApplicationService;

    @PostMapping
    public void  createApplication(@RequestBody @Valid ApplicationRequest applicationRequest) {
        this.cardsApplicationService.createApplication(applicationRequest);
    }



}