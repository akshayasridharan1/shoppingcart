package com.jpaexample.demo.project.controller;

import com.jpaexample.demo.project.model.Tax;
import com.jpaexample.demo.project.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcart/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/add")
    public Tax createTax(@RequestBody Tax tax)
    {
        return taxService.CreateTax(tax);
    }
}
