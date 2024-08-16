package com.config.homework.service.serviceImpl;

import com.config.homework.model.dto.request.ProductRequest;
import com.config.homework.model.dto.response.ProductResponse;
import com.config.homework.model.entities.Product;
import com.config.homework.repository.ProductRepository;
import com.config.homework.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity()).toResponse();
    }

    @Override
    public List<ProductResponse> getAllProducts(int page, int size, String sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy.toLowerCase());
        Pageable pageable = PageRequest.of(page-1, size, sort);
        Page<Product> products = productRepository.findAll(pageable);
        return products.getContent().stream().map(Product::toResponse).toList();
    }
}
