package com.config.homework.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "products_orders")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Product product;
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Order order;
    @Column(nullable = false)
    private Integer quantity;
}
