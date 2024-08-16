package com.config.homework.model.entities;

import com.config.homework.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;

    public ProductResponse toResponse() {
        return new ProductResponse(this.id,this.productName,this.unitPrice,this.description);
    }
}
