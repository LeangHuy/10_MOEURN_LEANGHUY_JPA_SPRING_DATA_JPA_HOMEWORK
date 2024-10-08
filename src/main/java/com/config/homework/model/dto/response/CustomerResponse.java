package com.config.homework.model.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponse {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private EmailResponse email;
}
