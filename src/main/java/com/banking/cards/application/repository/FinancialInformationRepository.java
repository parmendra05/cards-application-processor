package com.banking.cards.application.repository;

import com.banking.cards.application.entity.FinancialInformationEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface FinancialInformationRepository extends CassandraRepository<FinancialInformationEntity, String> {
}
