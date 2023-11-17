package com.hygieia.app.Models;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable=false, unique=true)
    private String roleName;

     
    private List<String> permissions=new ArrayList<>();

    @OneToMany(mappedBy="role")
    private List<AuthUser> Users;
}
