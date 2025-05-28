package com.banking.cards.application.api;

import com.banking.cards.application.model.PersonalInformation;
import com.banking.cards.application.model.request.ApplicationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/card-applications")
@Slf4j
public class CardsApplicationAPI {

    @PostMapping
    public void  createApplication(@RequestBody @Valid ApplicationRequest applicationRequest) {
        log.info("Creating application request: {}", applicationRequest);
    }

}