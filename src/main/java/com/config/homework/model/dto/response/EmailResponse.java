package com.config.homework.model.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmailResponse {
    private UUID id;
    private String email;
}
