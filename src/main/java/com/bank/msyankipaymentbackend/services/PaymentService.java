package com.bank.msyankipaymentbackend.services;

import com.bank.msyankipaymentbackend.handler.ResponseHandler;
import com.bank.msyankipaymentbackend.models.documents.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Mono<ResponseHandler> findAll();

    Mono<ResponseHandler> find(String id);

    Mono<ResponseHandler> create(Payment payment);

    Mono<ResponseHandler> update(String id, Payment payment);

    Mono<ResponseHandler> delete(String id);
}
