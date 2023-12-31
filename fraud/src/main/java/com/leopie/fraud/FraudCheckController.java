package com.leopie.fraud;

import com.leopie.clients.fraud.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record FraudCheckController(FraudCheckService service) {
    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId ) {
        boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer: {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
