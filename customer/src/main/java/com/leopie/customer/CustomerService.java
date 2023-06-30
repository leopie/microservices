package com.leopie.customer;

import com.leopie.clients.notifier.NotificationRequest;
import com.leopie.clients.fraud.FraudClient;
import com.leopie.clients.notifier.NotifierClient;
import com.leopie.clients.fraud.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
                              NotifierClient notifierClient) {

    public static final String CORRECTLY_REGISTERED_USER_MESSAGE = "Correctly registered user with id %d";

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastname(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        //TODO check if toCustomerEmail is valid
        //TODO check if toCustomerEmail is not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse!=null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message(CORRECTLY_REGISTERED_USER_MESSAGE.formatted(customer.getId()))
                .build();

        //TODO make async (add to a queue)
        notifierClient.sendNotification(notificationRequest);
    }
}
