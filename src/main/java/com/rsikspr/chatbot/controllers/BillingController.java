package com.rsikspr.chatbot.controllers;

import com.rsikspr.chatbot.entity.Billing;
import com.rsikspr.chatbot.services.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/billings")
public class BillingController {
    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping
    public List<Billing> getAllBillings() {
       return billingService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillingById(@PathVariable Long id) {
        Optional<Billing> foundBilling = billingService.getById(id);

        if (foundBilling.isPresent()) {
            return ResponseEntity.ok(foundBilling.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by/{customer}")
    public List<Billing> getBillingsByCustomer(@PathVariable String customer) {
        return billingService.getByCustomer(customer);
    }
}
