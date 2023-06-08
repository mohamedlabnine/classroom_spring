package com.example.Spring12.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @OneToOne
    @JoinColumn(name = "account_id")
    private AppUser account ;

    @OneToMany(mappedBy = "ensignement", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Classe> classList = new ArrayList<>();

    public Teacher(AppUser appUser) {
        this.account = appUser;
    }
}
