package com.config.homework.model.entities;

import com.config.homework.model.dto.response.EmailResponse;
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
    @OneToOne(mappedBy = "email")
    private Customer customer;

    public EmailResponse toResponse() {
        return new EmailResponse(this.id,this.email);
    }
}
