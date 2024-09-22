package com.neoflexapplicant.vacpaycalc4neoflex.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//@AllArgsConstructor
public class VacPayEntity {
    @Getter
    @Setter
    public String info;
    @Getter
    @Setter
    public BigDecimal amountOfPayment;
    public VacPayEntity(String info, BigDecimal amountOfPayment){
        this.info = info;
        this.amountOfPayment = amountOfPayment;
    }
}
