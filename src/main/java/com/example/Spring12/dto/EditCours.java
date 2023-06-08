package com.example.Spring12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditCours {
    private int id_cours ;
    private String name ;
    private String pdf_name ;
    private String description ;
}
