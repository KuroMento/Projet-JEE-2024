package fr.cyu.jee;

import java.util.regex.Pattern;
public class ModelValidator {
    public static void validateParameter(String parameter){
        if( parameter == null ){
            throw new IllegalArgumentException("A parameter was null !");
        }
        if( parameter.isEmpty() ){
            throw new IllegalArgumentException("A parameter is empty !");
        }
        if( parameter.isBlank() ){
            throw new IllegalArgumentException("A parameter is blank !");
        }
    }

    public static void validateDouble(String number){
        validateParameter(number);
        if( !number.matches("[-+]?[0-9]*\\.?[0-9]+") ){
            throw new IllegalArgumentException("A numeric parameter ( " + number + " ) is not represented as one !");
        }
    }

    public static void validateMail(String email){
        validateParameter(email);
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if( !Pattern.matches(emailRegex, email) ){
            throw new IllegalArgumentException("The email format is invalid !");
        }
    }
}
