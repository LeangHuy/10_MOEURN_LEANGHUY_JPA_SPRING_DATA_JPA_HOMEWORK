package com.config.homework.service;

import com.config.homework.model.dto.request.ProductRequest;
import com.config.homework.model.dto.response.ProductResponse;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts(int page, int size, String sortBy, Sort.Direction sortDirection);
    ProductResponse getProductById(UUID productId);
    ProductResponse updateProductById(UUID productId,ProductRequest productRequest);
    void deleteProductById(UUID productId);

}
