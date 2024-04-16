package com.example.payments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
@ExceptionHandler(CustomerNotFoundException.class)
public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exception){
   CustomerErrorResponse customerErrorResponse=new CustomerErrorResponse();
   customerErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
   customerErrorResponse.setMessage(exception.getMessage());
   customerErrorResponse.setTimestamp(System.currentTimeMillis());
   customerErrorResponse.setErrorType("Customer Not Found");
    return new ResponseEntity<>(customerErrorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficentBalanceException.class)
    public ResponseEntity<CustomerErrorResponse>handleException(InsufficentBalanceException exception){
        CustomerErrorResponse customerErrorResponse=new CustomerErrorResponse();
        customerErrorResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        customerErrorResponse.setMessage(exception.getMessage());
        customerErrorResponse.setTimestamp(System.currentTimeMillis());
        customerErrorResponse.setErrorType("Insufficient Balance");
        return new ResponseEntity<>(customerErrorResponse,HttpStatus.NOT_FOUND);
    }

}
