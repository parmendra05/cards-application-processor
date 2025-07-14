package com.banking.cards.application.repository;

import com.banking.cards.application.entity.PersonalCardKey;
import com.banking.cards.application.entity.PersonalInformationEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface PersonalInformationRepository extends CassandraRepository<PersonalInformationEntity, PersonalCardKey> {
    Optional<PersonalInformationEntity> findByCorrelationId(String correlationId);
}
