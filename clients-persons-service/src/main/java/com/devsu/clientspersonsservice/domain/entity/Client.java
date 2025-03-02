package com.devsu.clientspersonsservice.domain.entity;

import lombok.*;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Person {
    
    private String password;
    private Boolean status;
    private Boolean isDelete = false;

}

