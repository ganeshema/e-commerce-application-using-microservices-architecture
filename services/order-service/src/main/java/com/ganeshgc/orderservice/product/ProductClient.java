package com.ganeshgc.orderservice.product;

import com.ganeshgc.orderservice.exception.BussinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@Service
@RequiredArgsConstructor
public class ProductClient {


    @Value("$(application.config.product-url}")
    private String productUrl;

    // RestTemplate bean for making REST API calls
    private final RestTemplate restTemplate;

    /**
     * Sends a list of purchase requests to the Product Service and retrieves the responses.
     *
     * @param requests List of purchase requests containing product and purchase details
     * @return List of purchase responses from the Product Service
     */
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requests) {
        // Create HTTP headers and set the Content-Type to JSON
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // Create an HttpEntity object with the purchase request payload and headers
        HttpEntity<List<PurchaseRequest>> entity = new HttpEntity<>(requests, headers);

        // Define the expected response type (List<PurchaseResponse>) using ParameterizedTypeReference
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {};

        // Make an HTTP POST request to the Product Service's "purchase" endpoint
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase", // The URL for the "purchase" endpoint
                POST,                     // HTTP method: POST
                entity,                   // HTTP entity containing the request data
                responseType              // Expected response type
        );

        // Check if the response indicates an error
        if (responseEntity.getStatusCode().isError()) {
            // Throw a custom BusinessException with an error message
            throw new BussinessException("An error occurred when trying to retrieve the list of purchases: "
                    + responseEntity.getStatusCode());
        }

        // Return the body of the response, which is a list of PurchaseResponse objects
        return responseEntity.getBody();
    }
}