package com.devsu.accountsmovementsservice.domain.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String id;
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
}
