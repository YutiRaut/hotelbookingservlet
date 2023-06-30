/*package com.example.hotelbookingservlet.Common;

import com.example.hotelbookingservlet.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidation {

    public static List<ErrorUtil> validateCredentials(User user) {
        List<ErrorUtil> errorList = new ArrayList<>();
        if(Validation.isEmpty(user.getEmail())){
        errorList.add(new ErrorUtil("Please Enter Email"));
        }
        if(Validation.isEmpty(user.getPassword())){
            errorList.add(new ErrorUtil("Please Enter Password"));
        }
        return  errorList;

    }

    public static List<ErrorUtil> validateUser(User user){
        List<ErrorUtil> errorList = new ArrayList<>();

        if(Validation.isEmpty(user.getName())){
            errorList.add(new ErrorUtil("Please Enter Name!!"));
        }

        if(Validation.isEmpty(user.getEmail())){
            errorList.add(new ErrorUtil("Please Enter Email!!"));
        }

        if(Validation.isEmpty(user.getContact())){
            errorList.add(new ErrorUtil("Please Enter ContactNo!!"));
        }

        if(Validation.isEmpty(user.getPassword())){
            errorList.add(new ErrorUtil("Please Enter Password!!"));
        }
        if(Validation.isEmpty(String.valueOf(user.getRole()))){
            errorList.add(new ErrorUtil("Please Enter role!!"));
        }

        return errorList;
    }

}*/
