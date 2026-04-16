package com.ecommerce.zahir.entities;

import com.ecommerce.zahir.enums.RoleName;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "roles")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    @Column(length = 255)
    private String description;
    
}
