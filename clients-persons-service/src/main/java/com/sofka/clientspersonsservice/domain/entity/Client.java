package com.sofka.clientspersonsservice.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Person {

    @Column(name = "unique_client_id", unique = true, nullable = false)
    private String clientId;

    private String password;
    private Boolean status;
}

