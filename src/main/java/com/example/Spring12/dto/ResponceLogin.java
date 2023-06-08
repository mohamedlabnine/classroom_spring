package com.example.Spring12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponceLogin {
    private int id_user ;
    private  String role ;
    private  int Status ;
    private  String Token ;


    public ResponceLogin(int status) {
        Status = status;
    }
}
