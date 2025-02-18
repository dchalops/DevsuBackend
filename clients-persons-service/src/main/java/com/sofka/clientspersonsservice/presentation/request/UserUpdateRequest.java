package com.sofka.clientspersonsservice.presentation.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import com.sofka.clientspersonsservice.domain.entity.UserDetails;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "Id is required")
    private String id;
    private String username;
    private String password;
    private UserDetails userDetails;
}
