package com.jpaexample.demo.project.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Tax {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private BigDecimal taxRate;

    private String taxType;

    public Tax() {
    }

    public Tax(BigDecimal taxRate, String taxType) {
        this.taxRate = taxRate;
        this.taxType = taxType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
}
