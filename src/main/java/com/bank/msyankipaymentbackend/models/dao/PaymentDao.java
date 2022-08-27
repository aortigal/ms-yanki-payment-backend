package com.bank.msyankipaymentbackend.models.dao;

import com.bank.msyankipaymentbackend.models.documents.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentDao extends ReactiveMongoRepository<Payment, String> {
}
