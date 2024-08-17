package com.config.homework.model.entities;

import com.config.homework.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;


import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private double unitPrice;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<ProductOrder> productOrders;

    public ProductResponse toResponse() {
        return new ProductResponse(this.id,this.productName,this.unitPrice,this.description);
    }
}
