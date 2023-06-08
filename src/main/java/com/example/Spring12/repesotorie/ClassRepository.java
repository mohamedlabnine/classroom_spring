package com.example.Spring12.repesotorie;

import com.example.Spring12.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Classe , Integer> {
    Optional<Classe> findByCode(String code) ;
    Optional<Classe> findById(int id) ;
}
