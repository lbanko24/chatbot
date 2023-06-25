package com.rsikspr.chatbot.services;

import com.rsikspr.chatbot.entity.Billing;
import com.rsikspr.chatbot.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {
    private final BillingRepository billingRepository;

    public BillingServiceImpl(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Override
    public Billing createBilling(String customer) {
        Billing billing = new Billing();
        billing.setCustomer(customer);
        return billingRepository.save(billing);
    }

    @Override
    public List<Billing> getAll() {
        return billingRepository.findAll();
    }

    @Override
    public Optional<Billing> getById(Long id) {
        return billingRepository.findById(id);
    }

    @Override
    public List<Billing> getByCustomer(String customer) {
        return billingRepository.getByCustomer(customer);
    }
}
