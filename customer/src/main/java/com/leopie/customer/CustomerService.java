package com.leopie.customer;

import com.leopie.fraud.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastname(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        //TODO check if email is valid
        //TODO check if email is not taken
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse!=null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        //TODO send notification
    }
}
