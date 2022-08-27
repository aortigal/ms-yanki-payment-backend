package com.bank.msyankipaymentbackend.services.impl;

import com.bank.msyankipaymentbackend.constants.Constant;
import com.bank.msyankipaymentbackend.handler.ResponseHandler;
import com.bank.msyankipaymentbackend.models.dao.PaymentDao;
import com.bank.msyankipaymentbackend.models.documents.Payment;
import com.bank.msyankipaymentbackend.models.utils.DataEvent;
import com.bank.msyankipaymentbackend.producer.KafkaProducer;
import com.bank.msyankipaymentbackend.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao dao;

    @Autowired
    private KafkaProducer kafkaProducer;
    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public Mono<ResponseHandler> findAll() {
        log.info("[INI] findAll Payment");
        return dao.findAll()
                .doOnNext(payment -> log.info(payment.toString()))
                .collectList()
                .map(payments -> new ResponseHandler(Constant.RESPONSE_DONE, HttpStatus.OK, payments))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] findAll Payment"));
    }

    @Override
    public Mono<ResponseHandler> find(String id) {
        log.info("[INI] find Payment");
        return dao.findById(id)
                .doOnNext(payment -> log.info(payment.toString()))
                .map(payment -> new ResponseHandler(Constant.RESPONSE_DONE, HttpStatus.OK, payment))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] find Payment"));
    }

    @Override
    public Mono<ResponseHandler> create(Payment payment) {
        log.info("[INI] create Payment");
        DataEvent<Payment> dataEvent = new DataEvent<>();
        dataEvent.setId(UUID.randomUUID().toString());
        dataEvent.setProcess(Constant.PROCESS_PAYMENT_CREATE);
        dataEvent.setDateEvent(LocalDateTime.now());
        dataEvent.setData(payment);

        kafkaProducer.sendMessage(dataEvent);
        log.info("[END] create Payment");
        return Mono.just(new ResponseHandler(Constant.RESPONSE_DONE, HttpStatus.OK, payment));
    }

    @Override
    public Mono<ResponseHandler> update(String id, Payment payment) {
        log.info("[INI] update Payment");
        DataEvent<Payment> dataEvent = new DataEvent<>();
        dataEvent.setId(UUID.randomUUID().toString());
        dataEvent.setProcess(Constant.PROCESS_PAYMENT_UPDATE);
        dataEvent.setDateEvent(LocalDateTime.now());

        payment.setId(id);
        dataEvent.setData(payment);

        kafkaProducer.sendMessage(dataEvent);
        log.info("[END] update Payment");
        return Mono.just(new ResponseHandler(Constant.RESPONSE_DONE, HttpStatus.OK, payment));
    }

    @Override
    public Mono<ResponseHandler> delete(String id) {
        log.info("[INI] delete Payment");

        return dao.existsById(id).flatMap(check -> {
            if (check)
                return dao.deleteById(id).then(Mono.just(new ResponseHandler(Constant.RESPONSE_DONE, HttpStatus.OK, null)));
            else
                return Mono.just(new ResponseHandler(Constant.RESPONSE_NOT_FOUND, HttpStatus.NOT_FOUND, null));
        }).doFinally(fin -> log.info("[END] delete Payment"));
    }
}
