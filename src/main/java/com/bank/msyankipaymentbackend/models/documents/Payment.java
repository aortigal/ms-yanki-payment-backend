package com.bank.msyankipaymentbackend.models.documents;

import com.bank.msyankipaymentbackend.models.utils.Audit;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    private BigDecimal amount;

    @NotNull(message = "customerPhone must not be null")
    private String customerPhone;

}
