package com.example.Spring12.controller;

import com.example.Spring12.dto.Login;
import com.example.Spring12.dto.Register;
import com.example.Spring12.dto.ResponceLogin;
import com.example.Spring12.dto.ResponceRegister;
import com.example.Spring12.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/Auth")
@ResponseBody
@AllArgsConstructor
public class AuthController {
    private AuthService authService ;

    @PostMapping(path = "/login")
    public ResponceLogin login(@RequestBody Login login){
        return authService.login(login) ;
    }


    @PostMapping(path = "/register")
    public ResponceRegister regestration(@RequestBody Register register){
        return  register.getIs_Student() ? this.authService.registerAsStudent(register) : this.authService.registerAsTeacher(register) ;
    }

    @GetMapping("/reset_password/{email}")
    public ResponceRegister  reset_password(@PathVariable("email") String email){
        return  this.authService.reset_password(email) ;
    }
}
