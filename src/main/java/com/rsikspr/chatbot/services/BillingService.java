package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Billing;

import java.util.List;
import java.util.Optional;

public interface BillingService {
    Billing createBilling(String customer);

    List<Billing> getAll();

    Optional<Billing> getById(Long id);

    List<Billing> getByCustomer(String customer);
}
