package com.ganeshgc.orderservice.order;

import com.ganeshgc.orderservice.customer.CustomerClient;
import com.ganeshgc.orderservice.exception.BussinessException;
import com.ganeshgc.orderservice.kafka.OrderConfirmation;
import com.ganeshgc.orderservice.kafka.OrderProducer;
import com.ganeshgc.orderservice.orderLine.OrderLineRequest;
import com.ganeshgc.orderservice.orderLine.OrderLineService;
import com.ganeshgc.orderservice.payment.PaymentClient;
import com.ganeshgc.orderservice.payment.PaymentRequest;
import com.ganeshgc.orderservice.product.ProductClient;
import com.ganeshgc.orderservice.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        //check the customer--> (openFein)
        var customer=this.customerClient.getCustomerById(request.customerId())
                .orElseThrow(()->new BussinessException("Can not create order :: No customer found with the provided customer id"));

        //purchase the products---> pruduct ms(RestTamplate)
        var purchasedProducts=this.productClient.purchaseProducts(request.products());


        //persist order
        var order=this.repository.save(mapper.toOrder(request));

        //persist orderline
        for(PurchaseRequest purchaseRequest:request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //start payment process
        var paymentRequest=new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);



        // send the confirmation --> notification ms(kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }





    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("Order with id %s not found", orderId)));
    }
}
