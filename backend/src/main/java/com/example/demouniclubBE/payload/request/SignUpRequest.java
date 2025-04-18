package com.example.demouniclubBE.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull(message = "Email can't null")
    @NotBlank(message = "Email can't blank")
    @Email
    String email;

    @NotNull(message = "Password can't null")
    @NotBlank(message = "Password can't blank")
    String password;
}
