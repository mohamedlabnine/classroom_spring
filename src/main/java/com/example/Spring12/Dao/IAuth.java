package com.example.Spring12.Dao;

import com.example.Spring12.dto.Login;
import com.example.Spring12.dto.Register;
import com.example.Spring12.dto.ResponceLogin;
import com.example.Spring12.dto.ResponceRegister;

public interface IAuth {
    public ResponceLogin login(Login login)  ;
    public ResponceRegister registerAsStudent(Register register) ;
    public ResponceRegister registerAsTeacher(Register register) ;
    public ResponceRegister reset_password(String email) ;
}
