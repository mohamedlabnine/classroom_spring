package com.example.Spring12.repesotorie;

import com.example.Spring12.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser , Integer> {
    Optional<AppUser> findByEmail(String email) ;
    Optional<AppUser> findById(int id) ;
}
