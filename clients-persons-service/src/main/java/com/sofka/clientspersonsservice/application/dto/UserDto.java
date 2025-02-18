package com.sofka.clientspersonsservice.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sofka.clientspersonsservice.domain.entity.UserDetails;

import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String username;
    private String email;
    private UserDetails userDetails;
}
