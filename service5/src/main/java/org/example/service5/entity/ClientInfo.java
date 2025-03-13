package org.example.service5.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ClientInfo {
    @Id
    private Long id;
    private String pib;
    private String address;
    private String contacts;
}
