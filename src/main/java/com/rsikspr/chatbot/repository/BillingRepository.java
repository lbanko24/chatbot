package com.rsikspr.chatbot.repository;

import com.rsikspr.chatbot.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> getByCustomer(String customer);
}
