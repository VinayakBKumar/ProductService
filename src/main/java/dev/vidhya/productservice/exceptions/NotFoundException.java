package dev.vidhya.productservice.exceptions;


import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {

    public NotFoundException(String message){
        super(message);
    }
}
