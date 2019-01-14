package com.jpaexample.demo.project.service;

import com.jpaexample.demo.project.model.Tax;
import com.jpaexample.demo.project.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    @Autowired
    TaxRepository taxRepository;

    public Tax CreateTax(Tax tax)
    {
        return taxRepository.save(tax);
    }

    public Tax findByTaxType(String taxType)
    {
        return taxRepository.findByTaxType(taxType);
    }

}
