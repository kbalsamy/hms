package com.hygieia.Entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable=false, unique=true)
    private String roleName;

    @ManyToMany(mappedBy="roles")
    private List<User> Users;
}
