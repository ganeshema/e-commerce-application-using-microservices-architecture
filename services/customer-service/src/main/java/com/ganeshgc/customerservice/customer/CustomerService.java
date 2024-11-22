package com.ganeshgc.customerservice.customer;

import com.ganeshgc.customerservice.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;


    public String createCustomer(CustomerRequest request) {
        var customer=customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer=customerRepository.findById(request.id()).orElseThrow(()->new CustomerNotFoundException(
                String.format("Can not update customer :: No customer found with the provided id==> %s", request.id())
        ));
        mergerCustomer(customer,request);
        customerRepository.save(mapper.toCustomer(request));

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());

    }

    public Boolean existCustomer(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        var customer=customerRepository.findById(customerId).orElseThrow(()->
                new CustomerNotFoundException(String.format("Can not find customer :: %s", customerId)));
        CustomerResponse customerResponse=mapper.fromCustomer(customer);
        return customerResponse;


    }

    public void deleteCustomerById(String customerId) {
        var customer=customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException(
                String.format("Can not find customer :: %s", customerId)
        ));
        customerRepository.deleteById(customerId);
    }
}
