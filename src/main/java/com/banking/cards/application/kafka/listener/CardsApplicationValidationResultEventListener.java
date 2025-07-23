package com.banking.cards.application.kafka.listener;

import com.banking.cards.application.avro.ApplicationDataAvro;
import com.banking.cards.application.avro.ApplicationValidationResultEvent;
import com.banking.cards.application.service.CardsApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardsApplicationValidationResultEventListener {

    private final CardsApplicationService cardsApplicationService;

    @KafkaListener(topics = "vnd_cards_application_submitted_event", groupId = "application-submitted-vent-group")
    public void getData(ConsumerRecord<String, ApplicationValidationResultEvent> consumerRecord){
        log.info("Received Application Submitted Event: {}", consumerRecord.value().getCorrelationID());
        ApplicationValidationResultEvent applicationValidationResultEvent = consumerRecord.value();
        this.cardsApplicationService.updateApplication(applicationValidationResultEvent);
    }

}
