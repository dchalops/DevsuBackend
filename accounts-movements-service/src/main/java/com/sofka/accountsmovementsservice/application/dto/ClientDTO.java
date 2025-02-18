package com.sofka.accountsmovementsservice.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String clientId;
    private String name;
    private Boolean status;
}
