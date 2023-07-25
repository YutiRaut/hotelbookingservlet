package com.example.hotelbookingservlet.Common;

import com.example.hotelbookingservlet.DTO.JPASignupDto;
import com.example.hotelbookingservlet.DTO.SignupDto;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserValidation{

    public static List<Error> validateUser(SignupDto signupDto) {
        List<Error> errorList = new ArrayList<>();

        if(Validation.isEmpty(signupDto.getName())){
            errorList.add(new Error("name reqired"));
        }
        if(Validation.isEmpty(signupDto.getEmail())){
            errorList.add(new Error("email reqired"));
        }
        if(Validation.isEmpty(signupDto.getContact())){
            errorList.add(new Error("contact reqired"));
        }
        if(Validation.isEmpty(signupDto.getPassword())){
            errorList.add(new Error("password reqired"));
        }
        if(Validation.isEmpty(signupDto.getRole())){
            errorList.add(new Error("role reqired"));
        }
return errorList;
    }
    public static List<Error> JPAvalidateUser(JPASignupDto signupDto) {
        List<Error> errorList = new ArrayList<>();

        if(Validation.isEmpty(signupDto.getName())){
            errorList.add(new Error("name reqired"));
        }
        if(Validation.isEmpty(signupDto.getEmail())){
            errorList.add(new Error("email reqired"));
        }
        if(Validation.isEmpty(signupDto.getContact())){
            errorList.add(new Error("contact reqired"));
        }
        if(Validation.isEmpty(signupDto.getPassword())){
            errorList.add(new Error("password reqired"));
        }
        if(Validation.isEmpty(signupDto.getRole())){
            errorList.add(new Error("role reqired"));
        }
        return errorList;
    }


    }

