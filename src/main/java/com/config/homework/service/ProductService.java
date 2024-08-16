package com.config.homework.service;

import com.config.homework.model.dto.request.ProductRequest;
import com.config.homework.model.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
}
