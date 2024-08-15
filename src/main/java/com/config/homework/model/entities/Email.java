package com.config.homework.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne(mappedBy = "email", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, optional = false)
    private Customer customer;
}