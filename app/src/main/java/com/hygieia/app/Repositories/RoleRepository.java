package com.hygieia.app.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hygieia.app.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> 
{

//  @Query(value = "Select * from roles u where u.role-name=?1", nativeQuery = true)
//     Role GetrolebyRoleName(@Param("role-name") String roleName);

     Boolean existsByRoleName(String rolename);

    Optional<Role> findByRoleName(String name);
    
   //   @Query("update roles r set r.role_name = :name WHERE r.role_id = ?1")
   //   void updateRole(@Param("role_id") int id, @Param("role_name") List<String> permisList);
}
