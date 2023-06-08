package com.example.Spring12.repesotorie;

import com.example.Spring12.entity.AppUser;
import com.example.Spring12.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findById(int id) ;
    Optional<Teacher> findByAccount(AppUser user) ;
}
