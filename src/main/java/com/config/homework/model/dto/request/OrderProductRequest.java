package com.config.homework.model.dto.request;

import com.config.homework.model.entities.Order;
import com.config.homework.model.entities.Product;
import com.config.homework.model.entities.ProductOrder;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class OrderProductRequest {
    @NotNull
    private Integer quantity;
    @NotNull
    private UUID productId;

    public ProductOrder toEntity(Order order, Product product){
        return new ProductOrder(null,product,order,this.quantity);
    }
}
