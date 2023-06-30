package com.example.hotelbookingservlet.Common;

import java.util.ArrayList;
import java.util.List;

public class ErrorUtil {
    private List<String> errorMessages;
    public ErrorUtil(){
        errorMessages = new ArrayList<>();
    }
        public void addErrorMessage(String errorMessage) {
            errorMessages.add(errorMessage);
        }
        public List<String> getErrorMessages(){
        return errorMessages;
        }
    }

