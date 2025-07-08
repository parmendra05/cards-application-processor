package com.banking.cards.application.kafka.publisher;

import com.banking.cards.application.avro.ApplicationDataAvro;
import com.banking.cards.application.config.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CardsApplicationSubmitPublisher {

    private final KafkaTemplate<String, ApplicationDataAvro> kafkaTemplate;
    private final Topics topics;

    public void publishApplicationSubmitEvent(ApplicationDataAvro applicationDataAvro){
        log.info("Preparing to publish application-submit-event to topic {} for the correlationId {}",
                topics.getApplicationSubmittedEvent(),applicationDataAvro.getCorrelationID());
        try{
            this.kafkaTemplate.send(topics.getApplicationSubmittedEvent(), applicationDataAvro);
            log.info("Successfully published application-submit-event to topic {} for the correlationId {}",
                    topics.getApplicationSubmittedEvent(),applicationDataAvro.getCorrelationID());
        }catch(KafkaException kafkaException){
            log.error("Error while publishing application-submit-event to topic {} for the correlationId {}",
                    topics.getApplicationSubmittedEvent(),applicationDataAvro.getCorrelationID());
        }catch (Exception e){
            log.error("Unexpected error while publishing application-submit-event to topic {} for the correlationId {}",
                    topics.getApplicationSubmittedEvent(),applicationDataAvro.getCorrelationID());
        }
    }


}
