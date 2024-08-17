package com.config.homework.model.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class OrderResponse {
    private UUID id;
    private LocalDate orderDate;
    private double totalAmount;
    private String status;
    private Set<ProductResponse> productList;
}
