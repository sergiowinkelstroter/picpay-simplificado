package com.sergiowink.picpay.controllers.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TransferDTO(@DecimalMin(value = "0.01") @NotNull BigDecimal value,@NotNull Long payer,@NotNull Long payee) {

}
