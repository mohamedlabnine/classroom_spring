package com.example.Spring12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddClassFromTeacher {
    private int id_Teacher;
    private String name ;
    private String image ;
    private String branch ;
    private String description ;
}
