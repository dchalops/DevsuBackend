package com.sofka.clientspersonsservice.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity {
    
    @Column(nullable = false)
    private String name;
    private String gender;
    private int age;
    @Column(unique = true, nullable = false)
    private String id;
    private String address;
    private String phone;
}
