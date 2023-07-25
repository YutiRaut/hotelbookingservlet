package com.example.hotelbookingservlet.Common;

public class Validation {

    public static boolean isEmpty(String val){
        if(val==null || val.trim().isEmpty()){
            return true;
        }
        return  false;
    }

    public static boolean isEmpty(int val) {
      if(val==Integer.parseInt(null) ){

          return true;
      }
        return  false;
    }

}
