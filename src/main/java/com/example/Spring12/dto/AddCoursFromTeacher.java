package com.example.Spring12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCoursFromTeacher {
    private int id_Class ;
    private String name ;
    private String pdf_name ;
    private String description ;
}
