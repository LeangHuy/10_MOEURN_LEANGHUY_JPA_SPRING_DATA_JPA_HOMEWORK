package com.config.homework.model.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductResponse {
    private UUID id;
    private String productName;
    private BigDecimal unitPrice;
    private String description;
}
