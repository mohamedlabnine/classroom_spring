package com.example.Spring12.service;

import com.example.Spring12.Dao.IAuth;
import com.example.Spring12.dto.*;
import com.example.Spring12.entity.AppUser;
import com.example.Spring12.entity.Student;
import com.example.Spring12.entity.Teacher;
import com.example.Spring12.repesotorie.AppUserRepository;
import com.example.Spring12.repesotorie.StudentRepository;
import com.example.Spring12.repesotorie.TeacherRepository;
import com.example.Spring12.security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuth {
    private final JwtTokenUtil jwtTokenUtil ;
    private final AuthenticationManager authenticationManager ;
    private final MailService mailService ;
    private final UserService userService ;
    private final PasswordEncoder passwordEncoder ;

    public AuthService(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager, MailService mailService, UserService userService,PasswordEncoder passwordEncoder) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.mailService = mailService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder ;
    }

    @Autowired
    private AppUserRepository appUserRepository ;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public ResponceLogin login(Login login){
        UserDetails userDetails = userService.loadUserByUsername(login.getEmail()) ;

        if(userDetails != null && passwordEncoder.matches(login.getPassword() , userDetails.getPassword()) ){
            AppUser user = appUserRepository.findByEmail(userDetails.getUsername()).get();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername() , userDetails.getPassword()) ;
            Authentication authentication = authenticationManager.authenticate(token) ;
            SecurityContextHolder.getContext().setAuthentication(authentication);

            int user_id ;
            if(user.getRole().equals(Role.ROLE_STUDENT.name())){
                user_id = studentRepository.findByAccount(user).get().getId() ;
            }
            else {
                user_id = teacherRepository.findByAccount(user).get().getId() ;
            }
            return new ResponceLogin(user_id , user.getRole() ,200 , jwtTokenUtil.generateToken(userDetails)) ;


        }
        return new ResponceLogin(404);
    }

    @Transactional
    public ResponceRegister registerAsStudent(Register register) {
        Optional<AppUser> appUser =  appUserRepository.findByEmail(register.getEmail());
        if (appUser.isEmpty()){
            AppUser user = new AppUser(register.getFirstname() ,
                    register.getLastname() ,
                    register.getEmail(),
                    register.getPassword() ,
                    Role.ROLE_STUDENT.name()) ;
            appUserRepository.save(user) ;
            studentRepository.save(new Student(user)) ;
            return new ResponceRegister(200 , "you are registered now") ;
        }
        else {
            return new ResponceRegister(409 , "This email is already registered") ;
        }
    }

    @Transactional
    public ResponceRegister registerAsTeacher(Register register) {
        Optional<AppUser> appUser =  appUserRepository.findByEmail(register.getEmail());
        if (appUser.isEmpty()){
            AppUser user = new AppUser(register.getFirstname() ,
                    register.getLastname() ,
                    register.getEmail(),
                    register.getPassword(),
                    Role.ROLE_TEACHER.name()) ;

            appUserRepository.save(user) ;
            teacherRepository.save(new Teacher(user)) ;
            return new ResponceRegister(200 , "you are registered now") ;
        }
        else {
            return new ResponceRegister(409 , "This email is already registered") ;
        }

    }


    public ResponceRegister reset_password(String email){
        Optional<AppUser> user = appUserRepository.findByEmail(email) ;
        if(!user.isEmpty()){
            mailService.sendMail(user.get().getEmail(), "Forget password" , "your password is : " + user.get().getPassword());
            return new ResponceRegister(200 , "Send") ;
        }
        return new  ResponceRegister(500,"No") ;
    }
}
