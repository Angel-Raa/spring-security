package com.caja.ideal.user;

import com.caja.ideal.user.UserModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserModels, Long> {
    Optional<UserModels> findByEmail(String email);

}
