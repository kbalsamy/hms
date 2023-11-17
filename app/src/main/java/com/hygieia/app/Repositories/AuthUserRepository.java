package com.hygieia.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hygieia.app.Models.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

    @Query(value = "Select * from users u where u.user_name=?1", nativeQuery = true)
    AuthUser GetuserbyUserName(@Param("username") String userName);

    Boolean existsByUserName(String username);

}
