package com.banking.cards.application.repository;

import com.banking.cards.application.entity.PersonalCardKey;
import com.banking.cards.application.entity.PersonalInformationEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PersonalInformationRepository extends CassandraRepository<PersonalInformationEntity, PersonalCardKey> {
    PersonalInformationEntity findByCorrelationId(String correlationId);
}
