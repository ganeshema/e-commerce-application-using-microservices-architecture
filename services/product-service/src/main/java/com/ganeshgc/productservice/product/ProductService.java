package com.ganeshgc.productservice.product;

import com.ganeshgc.productservice.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper  mapper;
    public Integer createProduct(ProductRequest request) {
        var product=mapper.toProduct(request);
        repository.save(product);
        return product.getId();
    }



    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        var productIds=request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts=repository.findAllByIdInOrderById(productIds);
        if(productIds.size()!=storedProducts.size()){
            throw new ProductPurchaseException("one or more products do not exist.");
        }
        var storedRequest=request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts=new ArrayList<ProductPurchaseResponse>();
        for(int i=0; i<storedProducts.size(); i++){
            var product=storedProducts.get(i);
            var productRequest=storedRequest.get(i);
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("not enough available quantity for the product having id "+product.getId());
            }
            var newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchasedProducts;
    }




    public ProductResponse findById(Integer productId) {
        return  repository.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException(String.format("Product with id [%s] not found", productId)));
    }

    public List<ProductResponse> findAll() {
        List<ProductResponse> products = repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
        return products;
    }
}
