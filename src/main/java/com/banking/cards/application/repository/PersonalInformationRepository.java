package com.banking.cards.application.repository;

import com.banking.cards.application.entity.PersonalCardKey;
import com.banking.cards.application.entity.PersonalInformationEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;


import java.util.Optional;

public interface PersonalInformationRepository extends CassandraRepository<PersonalInformationEntity, PersonalCardKey> {
    @Query("SELECT * FROM personal_information WHERE correlationId = ?0 ALLOW FILTERING")
    Optional<PersonalInformationEntity> findByCorrelationId(String correlationId);
}
