package com.example.Spring12.repesotorie;

import com.example.Spring12.entity.AppUser;
import com.example.Spring12.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(int id) ;

    Optional<Student> findByAccount(AppUser user) ;


}
