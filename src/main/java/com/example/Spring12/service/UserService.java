package com.example.Spring12.service;

import com.example.Spring12.dto.*;
import com.example.Spring12.entity.AppUser;
import com.example.Spring12.repesotorie.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository ;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(!appUser.isEmpty()){
            return new AppUserDetails(appUser.get()) ;
        }
        return  null ;
    }



}
