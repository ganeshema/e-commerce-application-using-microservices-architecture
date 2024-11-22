package com.ganeshgc.customerservice.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<Boolean> existCustomer(@PathVariable @Valid String customerId) {
        return ResponseEntity.ok(customerService.existCustomer(customerId));
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable @Valid String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable @Valid String customerId) {
        customerService.deleteCustomerById(customerId);
        String successMessage = String.format("Customer having id %s is successfully deleted.", customerId);
        return ResponseEntity.ok(successMessage); // Use .ok() to return a 200 status with the message
    }

}
