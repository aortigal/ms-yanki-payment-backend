package com.bank.msyankipaymentbackend.controllers;

import com.bank.msyankipaymentbackend.handler.ResponseHandler;
import com.bank.msyankipaymentbackend.models.documents.Payment;
import com.bank.msyankipaymentbackend.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/yanki/payment")
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public Mono<ResponseHandler> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseHandler> find(@PathVariable String id) {
        return paymentService.find(id);
    }

    @PostMapping
    public Mono<ResponseHandler> create(@Valid @RequestBody Payment payment) {
        return paymentService.create(payment);
    }

    @PutMapping("/{id}")
    public Mono<ResponseHandler> update(@PathVariable("id") String id,@Valid @RequestBody Payment payment) {
        return paymentService.update(id,payment);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseHandler> delete(@PathVariable("id") String id) {
        return paymentService.delete(id);
    }
}
