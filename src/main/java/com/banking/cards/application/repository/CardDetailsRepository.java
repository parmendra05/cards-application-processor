package com.banking.cards.application.repository;

import com.banking.cards.application.entity.CardDetailsEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CardDetailsRepository extends CassandraRepository<CardDetailsEntity, String> {
}
