package com.config.homework.controller;

import com.config.homework.model.dto.request.ProductRequest;
import com.config.homework.model.dto.response.ProductResponse;
import com.config.homework.service.ProductService;
import com.config.homework.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create new product.")
    private ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest productRequest)
    {
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .message("A new product is inserted successfully.")
                .payload(productService.createProduct(productRequest))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all products.")
    private ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ){
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .message("Get all products successfully.")
                .payload(productService.getAllProducts(page, size, sortBy, direction))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
