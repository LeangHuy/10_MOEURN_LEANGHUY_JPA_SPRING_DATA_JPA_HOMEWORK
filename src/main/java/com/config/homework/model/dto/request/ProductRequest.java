package com.config.homework.model.dto.request;


import com.config.homework.model.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductRequest {
    @NotNull
    @NotBlank
    private String productName;
    @Min(value = 0, message = "Unit price must be positive")
    private double unitPrice;
    @NotNull
    @NotBlank
    private String description;

    public Product toEntity(){
        return new Product(null,this.productName,this.unitPrice,this.description,null);
    }

    public Product toEntity(UUID id){
        return new Product(id,this.productName,this.unitPrice,this.description,null);
    }
}
