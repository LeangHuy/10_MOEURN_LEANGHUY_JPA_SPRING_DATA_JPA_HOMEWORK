package com.config.homework.model.entities;

import com.config.homework.model.dto.response.CustomerResponse;
import com.config.homework.model.dto.response.EmailResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phoneNumber;
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Email email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public CustomerResponse toResponse(){
        EmailResponse emailResponse = email.toResponse();
        return new CustomerResponse(this.id, this.name, this.address,this.phoneNumber, emailResponse);
    }

}
