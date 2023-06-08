package com.example.Spring12.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private String pdf_name ;
    private String description ;
    private Date date_creation ;
    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JsonIgnore
    @JoinColumn(name = "class_id", nullable = false)
    private Classe classe ;

    public Cours(String name, String pdf_name, String description , Classe classe) {
        this.name = name;
        this.pdf_name = pdf_name;
        this.description = description;
        this.date_creation = new Date();
        this.classe = classe;
    }


}
