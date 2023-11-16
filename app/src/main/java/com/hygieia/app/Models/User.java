package com.hygieia.app.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users",schema = "loginData")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false,unique=true)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="userId")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="roleId")})
    private List<Role> roles=new ArrayList<>();

    
}
