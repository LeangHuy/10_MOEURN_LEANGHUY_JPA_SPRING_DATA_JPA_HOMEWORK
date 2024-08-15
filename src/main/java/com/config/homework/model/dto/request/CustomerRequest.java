package com.config.homework.model.dto.request;

import com.config.homework.model.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerRequest {
    @NotNull
    @NotBlank
    private String customerName;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    @Pattern(
            regexp = "^(\\\\+[0-9]{1,3}[- ]?)?([0-9]{3,4})[- ]?([0-9]{3})[- ]?([0-9]{3})$",
            message = "Please ensure the phone number is in a valid format"
    )
    private String phoneNumber;
    @NotBlank
    @NotNull
    @Email
    private String email;

    public Customer toCustomerEntity(com.config.homework.model.entities.Email emails){
        return new Customer(null,this.customerName,this.address,this.phoneNumber,emails,null);
    }
    public com.config.homework.model.entities.Email toEmailEntity(String email){
        return new com.config.homework.model.entities.Email(null,email,null);
    }
}
