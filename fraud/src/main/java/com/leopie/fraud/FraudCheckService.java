package com.leopie.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckRepository repository) {
    public boolean isFraudulentCustomer(Integer customerId) {
        repository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
