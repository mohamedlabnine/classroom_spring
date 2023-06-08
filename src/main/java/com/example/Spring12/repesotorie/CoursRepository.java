package com.example.Spring12.repesotorie;

import com.example.Spring12.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Integer> {
    Optional<Cours> findById(int id) ;
}
