package com.config.homework.model.dto.response;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerOrderResponse {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private EmailResponse email;
    private Set<OrderResponse> orderList;
}
