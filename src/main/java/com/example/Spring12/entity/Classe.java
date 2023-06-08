package com.example.Spring12.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private String image ;
    private String branch ;
    private String description ;
    private Date date_created ;
    private String code ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JsonIgnore
    @JoinColumn(name = "ensignement_id", nullable = false)
    private Teacher ensignement ;

    @ManyToMany(mappedBy = "classList" )
    @JsonIgnore
    private List<Student> student ;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cours> coursList = new ArrayList<>();

    public Classe(String name,String image ,  String branch, String description, Teacher ensignement) {
        this.name = name;
        this.image = image ;
        this.branch = branch;
        this.description = description;
        this.date_created = new Date();
        this.code = UUID.randomUUID().toString();
        this.ensignement = ensignement;
    }

    public Classe(String name ,String image , String branch, String description, Date date , String code) {
        this.name = name;
        this.image = image ;
        this.branch = branch;
        this.description = description;
        this.date_created = date;
        this.code = code;
    }


}
