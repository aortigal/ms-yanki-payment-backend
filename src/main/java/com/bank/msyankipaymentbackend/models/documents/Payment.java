package com.bank.msyankipaymentbackend.models.documents;

import com.bank.msyankipaymentbackend.models.utils.Audit;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "payments")
public class Payment extends Audit {

    @Id
    private String id;

    @NotNull(message = "recipientName must not be null")
    private String recipientName;

    @NotNull(message = "recipientPhone must not be null")
    private String recipientPhone;

    @NotNull(message = "comissionAmount must not be null")
    private String comissionAmount;

    @NotNull(message = "amount must not be null")
    private float amount;

    @NotNull(message = "customerId must not be null")
    private String customerId;

}
